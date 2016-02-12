/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.controller;

import br.com.andreluizlunelli.webmvc.model.dao.ItemDao;
import br.com.andreluizlunelli.webmvc.model.dao.LancamentoDao;
import br.com.andreluizlunelli.webmvc.model.entity.Item;
import br.com.andreluizlunelli.webmvc.model.entity.Lancamento;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ANDRE
 */
@ManagedBean
@SessionScoped
public class MeuController {

    public void teste() {
        
        this.inserirItem();
        
//        LancamentoDao lancamentoDao = new LancamentoDao();
//
//        List<Lancamento> lancamentos = lancamentoDao.getLancamentos();
//        
//        Lancamento l = new Lancamento();
//        l.setObservacao("teste observacao");
//        l.setValorTotal(50);
//        l.setDataInicial(Calendar.getInstance());
//        l.setDataFinal(Calendar.getInstance());
//        lancamentoDao.inserir(l);
                
        System.out.println("passou por aqui");
        
    }
    
    public void inserirItem() {
        ItemDao itemDao = new ItemDao();
        Item item = new Item();
        item.setValor(16.58);
        item.setDescricao("descricao de teste de item");
        itemDao.inserir(item);
    }


}
