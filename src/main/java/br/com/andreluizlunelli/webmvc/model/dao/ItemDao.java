/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.model.dao;

import br.com.andreluizlunelli.webmvc.model.entity.Item;
import br.com.andreluizlunelli.webmvc.model.entity.Lancamento;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author ANDRE
 */
public class ItemDao extends BaseDAO implements Dao<Item> {    

    @Override
    public Item get(long id) {
        return (Item) getEntityManager().createNamedQuery("Item.find").setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Item> getAll() {
        return getEntityManager().createNamedQuery("Item.findAll").getResultList();        
    }

    @Override
    public void save(Item t) {
        salvar(t);
    }

    @Override
    public void update(Item t) {
        salvar(t);
    }

    @Override
    public void delete(Item t) {
        excluir(t);
    }
    
    
    
}
