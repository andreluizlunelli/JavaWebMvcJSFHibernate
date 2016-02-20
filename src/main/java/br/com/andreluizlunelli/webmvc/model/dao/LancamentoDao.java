/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.model.dao;

import br.com.andreluizlunelli.webmvc.model.entity.Lancamento;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

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
       
    public List<Lancamento> findAllMaisQueDezItens() {
        String queryString = "select T.*" +
                            " from (" +
                            "	select l.*, count(li.id) as qtd" +
                            "	from lancamento l " +
                            "	join lancamento_item li on (li.lancamento_oid = l.oid)" +
                            "	join item i on (i.oid = li.item_oid)" +
                            "	group by l.oid" +
                            "	order by l.vl_total desc" +
                            ") T" +
                            " where T.qtd > 10";
        Query query = getEntityManager().createNativeQuery(queryString);
        List<Lancamento> listaLancamentos = new ArrayList<>();
        List<Object> listaObjetos = query.getResultList();
        Lancamento l = null;
        Object[] o = null;        
        for (int i=0; i<listaObjetos.size(); i++) {
            o = (Object[]) listaObjetos.get(i);            
            int id = (Integer) o[0];
            Timestamp dataInicio = (Timestamp) o[1];
            Timestamp dataFim = (Timestamp) o[2];
            BigDecimal vlTotal = (BigDecimal) o[3];
            String observacao = (String) o[4];            
            
            l = new Lancamento();
            l.setId(id);
            l.setDataInicial(dataInicio);
            l.setDataFinal(dataFim);
            l.setValorTotal(vlTotal.floatValue());
            l.setObservacao(observacao);
            listaLancamentos.add(l);
        }       
        
        return listaLancamentos;
    }
}
