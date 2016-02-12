/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.controller.managedbean.item;

import br.com.andreluizlunelli.webmvc.model.dao.ItemDao;
import br.com.andreluizlunelli.webmvc.model.entity.Item;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ANDRE
 */
@ManagedBean
public class ListarItemMB {
    private ItemDao itemDao = new ItemDao();
    private static List<Item> listaItens = null;

    public List<Item> getListaItens() {
        if (listaItens == null) {
            listaItens = itemDao.getAll();
        }
        return listaItens;
    }

    public void setListaItens(List<Item> listaItem) {
        this.listaItens = listaItem;
    }
    
    
        
}
