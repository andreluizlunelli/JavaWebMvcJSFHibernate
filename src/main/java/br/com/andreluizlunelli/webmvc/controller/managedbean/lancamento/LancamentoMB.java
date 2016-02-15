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
    private static List<Lancamento> listaLancamento;

    @PostConstruct
    public void init() {
        lancamentoDao = new LancamentoDao();
        lancamento = new Lancamento();
    }              

    public List<Lancamento> getListaLancamento() {
        if (listaLancamento == null) {
            listaLancamento = lancamentoDao.getAll();
        }
        return listaLancamento;
    }

    public void setListaLancamento(List<Lancamento> listaLancamento) {
        listaLancamento = listaLancamento;
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
        Lancamento editado = (Lancamento) event.getObject();
        try {
            lancamentoDao.save(editado);
            msg = new FacesMessage("Lançamento editado");
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro na edição, tente mais tarde.", (String.valueOf((editado.getId()))));
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancelarEdicaoLinha(RowEditEvent event) {
        Lancamento editado = (Lancamento) event.getObject();
        msg = new FacesMessage("Edição cancelada", (String.valueOf((editado.getId()))));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
        
    public void excluirItem(Lancamento l) {
        try {
            if (listaLancamento.contains(l)) {
                lancamentoDao.delete(l);
                listaLancamento.remove(l);                
                lancamento = new Lancamento();
                msg = new FacesMessage("Exclusão", "Lançamento excluído com sucesso");
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro no cadastro, tente mais tarde.", null);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
