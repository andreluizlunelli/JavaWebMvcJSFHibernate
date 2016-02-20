/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.model.Lancamento;

import br.com.andreluizlunelli.webmvc.model.dao.LancamentoDao;
import br.com.andreluizlunelli.webmvc.model.entity.Lancamento;
import br.com.andreluizlunelli.webmvc.model.Lancamento.exceptions.LancamentoDaoVazio;
import br.com.andreluizlunelli.webmvc.model.Lancamento.exceptions.ListaLancamentosVazia;
import br.com.andreluizlunelli.webmvc.model.entity.Item;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ANDRE
 */
public class SelecionarLancamentosAlterarDescricao {

    private LancamentoDao lancamentoDao;
    private List<Lancamento> lancamentos;
    private static final String CONCATENAR = " - Possuem mais que 10 itens";

    public SelecionarLancamentosAlterarDescricao(LancamentoDao lancamentoDao) throws LancamentoDaoVazio {
        setLancamentoDao(lancamentoDao);
    }
    
    private void atualizarObservacao(Lancamento l, EntityManager em) {                
        String hqlAtualizar = "update Lancamento l set l.observacao = :observacao where l.id = :id";
        Query query = em.createQuery(hqlAtualizar);
        query.setParameter("observacao", l.getObservacao());
        query.setParameter("id", l.getId());
        int status = query.executeUpdate();          
    }

    public void executar() {
        try {
            List<Lancamento> tempListaLancamento = lancamentoDao.findAllMaisQueDezItens();
            if (tempListaLancamento == null || tempListaLancamento.isEmpty()) {
                return;
            }
            setLancamentos(tempListaLancamento);
            EntityManager em = lancamentoDao.getEntityManager();
            em.getTransaction().begin();       
            for (int i=0; i<lancamentos.size(); i++) {
                Lancamento l = lancamentos.get(i);
                l.setObservacao(l.getObservacao() + CONCATENAR);
                atualizarObservacao(l, em);                                
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) throws ListaLancamentosVazia {
        if (lancamentos == null || lancamentos.isEmpty()) {
            throw new ListaLancamentosVazia();
        }
        this.lancamentos = lancamentos;
    }

    public LancamentoDao getLancamentoDao() {
        return lancamentoDao;
    }

    public void setLancamentoDao(LancamentoDao lancamentoDao) throws LancamentoDaoVazio {
        if (lancamentoDao == null) {
            throw new LancamentoDaoVazio();
        }
        this.lancamentoDao = lancamentoDao;
    }

}
