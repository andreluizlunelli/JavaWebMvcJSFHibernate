/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.controller.managedbean.lancamento;

import br.com.andreluizlunelli.webmvc.model.dao.LancamentoDao;
import br.com.andreluizlunelli.webmvc.model.entity.Lancamento;
import br.com.andreluizlunelli.webmvc.view.util.ViewUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author ANDRE
 */
@ManagedBean
@ViewScoped
public class LancamentoMB {

    FacesMessage msg = null;
    private LancamentoDao lancamentoDao;
    private Lancamento lancamento;
    private static List<ListaLancamentoParserView.LancamentoParserView> listaLancamentoParserViews;

    @PostConstruct
    public void init() {
        lancamentoDao = new LancamentoDao();
        lancamento = new Lancamento();
    }              

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public void salvarLancamento() {
        try {
            lancamentoDao.save(lancamento);
            lancamento = new Lancamento();
            msg = new FacesMessage("Cadastro", "Lançamento cadastrado com sucesso");
//            this.setListaItens(null);
//            this.getListaItens();
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro no cadastro, tente mais tarde.", null);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onEditarLinha(RowEditEvent event) {
        ListaLancamentoParserView.LancamentoParserView lpvEditado = (ListaLancamentoParserView.LancamentoParserView) event.getObject();
        try {
            lancamentoDao.save(lpvEditado.lancamento1);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lançamento editado", (String.valueOf((lpvEditado.getId()))));
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro na edição, tente mais tarde.", (String.valueOf((lpvEditado.getId()))));
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancelarEdicaoLinha(RowEditEvent event) {
        Lancamento lancamentoEditado = (Lancamento) event.getObject();
        msg = new FacesMessage("Edição cancelada", (String.valueOf((lancamentoEditado.getId()))));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void excluirItem(ListaLancamentoParserView.LancamentoParserView l) {
        try {
            if (listaLancamentoParserViews.contains(l)) {
                lancamentoDao.delete(l.lancamento1);
                listaLancamentoParserViews.remove(l);
                
                lancamento = new Lancamento();
                msg = new FacesMessage("Exclusão", "Lançamento excluído com sucesso");
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro no cadastro, tente mais tarde.", null);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<ListaLancamentoParserView.LancamentoParserView> getListaLancamentoParserViews() {
        if (listaLancamentoParserViews == null) {
            listaLancamentoParserViews = this.obterListaParserView();
        }
        return listaLancamentoParserViews;
    }

    public void setListaLancamentoParserViews(List<ListaLancamentoParserView.LancamentoParserView> listaLancamentoParserViews) {
        LancamentoMB.listaLancamentoParserViews = listaLancamentoParserViews;
    }
    
//    classe que sera retirada daqui depois e passada para pacote de parsers
    public class ListaLancamentoParserView {

        public class LancamentoParserView implements Serializable {

            private long id;
            private double valorTotal;
            private String dataInicial;
            private String dataFinal;
            private String observacao;
            public Lancamento lancamento1;

            public LancamentoParserView(Lancamento l) {
                this.id = l.getId();
                this.observacao = l.getObservacao();
                this.valorTotal = l.getValorTotal();
                this.dataInicial = ViewUtil.dateToStr(l.getDataInicial().getTime());
                this.dataFinal = ViewUtil.dateToStr(l.getDataFinal().getTime());
                this.lancamento1 = l;
            }

            public long getId() {
                return id;
            }

            public double getValorTotal() {
                return valorTotal;
            }

            public String getDataInicial() {
                return dataInicial;
            }

            public String getDataFinal() {
                return dataFinal;
            }

            public String getObservacao() {
                return observacao;
            }

            @Override
            public int hashCode() {
                int hash = 7;
                hash = 29 * hash + (int) (Double.doubleToLongBits(this.valorTotal) ^ (Double.doubleToLongBits(this.valorTotal) >>> 32));
                hash = 29 * hash + Objects.hashCode(this.dataInicial);
                hash = 29 * hash + Objects.hashCode(this.dataFinal);
                hash = 29 * hash + Objects.hashCode(this.observacao);
                return hash;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null) {
                    return false;
                }
                if (getClass() != obj.getClass()) {
                    return false;
                }
                final LancamentoParserView other = (LancamentoParserView) obj;
                if (Double.doubleToLongBits(this.valorTotal) != Double.doubleToLongBits(other.valorTotal)) {
                    return false;
                }
                if (!Objects.equals(this.dataInicial, other.dataInicial)) {
                    return false;
                }
                if (!Objects.equals(this.dataFinal, other.dataFinal)) {
                    return false;
                }
                if (!Objects.equals(this.observacao, other.observacao)) {
                    return false;
                }
                return true;
            }                        

        }
        List<Lancamento> lancamentos;

        public ListaLancamentoParserView(List<Lancamento> lancamentos) {
            this.lancamentos = lancamentos;
        }

        private List<LancamentoParserView> getLista() {
            List<LancamentoParserView> lista = new ArrayList<LancamentoParserView>();
            for (Lancamento l : this.lancamentos) {
                lista.add(new LancamentoParserView(l));
            }
            return lista;
        }

    }

    public List<ListaLancamentoParserView.LancamentoParserView> obterListaParserView() {
        return new ListaLancamentoParserView(lancamentoDao.getAll()).getLista();
    }

}
