/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.model.dao;

import br.com.andreluizlunelli.webmvc.model.entity.Lancamento;
import java.util.List;

/**
 *
 * @author ANDRE
 */
public class LancamentoDao extends BaseDAO implements Dao<Lancamento>{

    @Override
    public Lancamento get(long id) {
        return (Lancamento) getEntityManager().createNamedQuery("Lancamento.find").setParameter("id", id).getResultList();        
    }

    @Override
    public List<Lancamento> getAll() {
        return getEntityManager().createNamedQuery("Lancamento.findAll").getResultList();        
    }

    @Override
    public void save(Lancamento t) {
        salvar(t);
    }

    @Override
    public void update(Lancamento t) {
        salvar(t);
    }

    @Override
    public void delete(Lancamento t) {
        excluir(t);
    }
       
    
}
