/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.controller.managedbean.lancamento;

import br.com.andreluizlunelli.webmvc.model.Lancamento.SelecionarLancamentosAlterarDescricao;
import br.com.andreluizlunelli.webmvc.model.Lancamento.exceptions.LancamentoDaoVazio;
import br.com.andreluizlunelli.webmvc.model.dao.ItemDao;
import br.com.andreluizlunelli.webmvc.model.dao.LancamentoDao;
import br.com.andreluizlunelli.webmvc.model.entity.Item;
import br.com.andreluizlunelli.webmvc.model.entity.Lancamento;
import java.util.ArrayList;
import java.util.List;
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
    private static List<Lancamento> listaLancamento;
    private String valorAutoCompleteSelecionado = "";

    //========= pesquisa
    private List<Item> listaItensEscolhidos; // esse aqui tem que ser a lista da entidade
    private List<Item> listaItens = null;
    private ItemDao itemDao;

    @PostConstruct
    public void init() {
        listaLancamento = null;
        itemDao = new ItemDao();
        lancamentoDao = new LancamentoDao();
        lancamento = new Lancamento();
        listaItensEscolhidos = new ArrayList<>();
    }

    private void atualizarListaDaTabela() {
        this.setListaLancamento(null);
        this.getListaLancamento();
    }

    public List<Item> getListaItensEscolhidos() {
        return listaItensEscolhidos;
    }

    public void setListaItensEscolhidos(List<Item> listaItensEscolhidos) {
        this.listaItensEscolhidos = listaItensEscolhidos;
    }

    public List<Item> completeMethodItem(String pesquisa) {
        pesquisa = pesquisa.toLowerCase();
        List<Item> todosItens = getListaItens();
        List<Item> itensFiltrados = new ArrayList();
        for (Item i : todosItens) {
            String descricao = i.getDescricao();
            descricao = descricao.toLowerCase();
            if (descricao.startsWith(pesquisa)) {
                itensFiltrados.add(i);
            }
        }
        return itensFiltrados;
    }

    public List<Item> getListaItens() {
        if (listaItens == null) {            
            listaItens = itemDao.getAll();
        }
        return listaItens;
    }

    public String getValorAutoCompleteSelecionado() {
        return valorAutoCompleteSelecionado;
    }

    public void setValorAutoCompleteSelecionado(String valorAutoCompleteSelecionado) {
        this.valorAutoCompleteSelecionado = valorAutoCompleteSelecionado;
    }

    public List<Lancamento> getListaLancamento() {
        if (listaLancamento == null) {
            listaLancamento = lancamentoDao.getAll();
        }
        return listaLancamento;
    }

    public void setListaLancamento(List<Lancamento> listaLancamento) {
        this.listaLancamento = listaLancamento;
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
            msg = new FacesMessage("", "Lançamento cadastrado com sucesso");
            atualizarListaDaTabela();
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro no cadastro, tente mais tarde.", null);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onEditarLinha(RowEditEvent event) {
        Lancamento editado = (Lancamento) event.getObject();
        List<Item> tempListaItens = editado.getListaItens();
        editado.setListaItens(new ArrayList<Item>());
        editado.setListaItens(tempListaItens);
        try {
            lancamentoDao.save(editado);
            msg = new FacesMessage("", "Lançamento editado");
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro na edição, tente mais tarde.", (String.valueOf((editado.getId()))));
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancelarEdicaoLinha(RowEditEvent event) {
        Lancamento editado = (Lancamento) event.getObject();
        msg = new FacesMessage("", "Edição cancelada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void excluirItem(Lancamento l) {
        try {
            if (listaLancamento.contains(l)) {
                lancamentoDao.delete(l);
                listaLancamento.remove(l);
                lancamento = new Lancamento();
                msg = new FacesMessage("", "Lançamento excluído com sucesso");
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro no cadastro, tente mais tarde.", null);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void selecionarEAlterarDescricao() {
        try {
            SelecionarLancamentosAlterarDescricao slad;
            slad = new SelecionarLancamentosAlterarDescricao(lancamentoDao);
            slad.executar();
            lancamentoDao.getEntityManager().clear(); // limpa o cache
            atualizarListaDaTabela();
        } catch (LancamentoDaoVazio ex) {
//            Logger.getLogger(LancamentoMB.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}
