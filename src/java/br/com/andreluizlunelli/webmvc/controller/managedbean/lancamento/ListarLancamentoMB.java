/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.controller.managedbean.lancamento;

import br.com.andreluizlunelli.webmvc.controller.managedbean.item.*;
import br.com.andreluizlunelli.webmvc.model.dao.ItemDao;
import br.com.andreluizlunelli.webmvc.model.dao.LancamentoDao;
import br.com.andreluizlunelli.webmvc.model.entity.Item;
import br.com.andreluizlunelli.webmvc.model.entity.Lancamento;
import br.com.andreluizlunelli.webmvc.view.util.ViewUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ANDRE
 */
@ManagedBean
public class ListarLancamentoMB {    
    public class ListaLancamentoParserView {    
        public class LancamentoParserView {
            private long id;
            private double valorTotal;
            private String dataInicial;
            private String dataFinal;
            private String observacao;
            public LancamentoParserView(Lancamento l) {
                this.id = l.getId();
                this.observacao = l.getObservacao();
                this.valorTotal = l.getValorTotal();
                this.dataInicial= ViewUtil.dateToStr(l.getDataInicial().getTime());
                this.dataFinal  = ViewUtil.dateToStr(l.getDataFinal().getTime());
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
    
    private LancamentoDao lancamentoDao = new LancamentoDao();
    
    public List<ListaLancamentoParserView.LancamentoParserView> obterLista() {
        return new ListaLancamentoParserView(lancamentoDao.getAll()).getLista();
    }
    
}
