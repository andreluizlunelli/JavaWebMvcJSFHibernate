/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.controller.managedbean.item;

import br.com.andreluizlunelli.webmvc.model.dao.ItemDao;
import br.com.andreluizlunelli.webmvc.model.entity.Item;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ANDRE
 */
@ManagedBean
public class SalvarItemMB {
    private ItemDao itemDao = new ItemDao();
    public Item item = new Item();
    
    public String salvarItem() {
        itemDao.save(item);
        return "listar";
    }

    public Item getItem() {
        return item;
    }        
        
}
