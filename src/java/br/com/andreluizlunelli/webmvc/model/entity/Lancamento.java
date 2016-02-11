
package br.com.andreluizlunelli.webmvc.model.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ANDRE
 */
@Entity
@Table(name = "lancamento")
@NamedQueries({
    @NamedQuery(name="Lancamento.findAll", query="SELECT c FROM Lancamento c"),
    @NamedQuery(name = "Lancamento.find", query = "SELECT i FROM Lancamento i WHERE i.id = ?")
})
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oid")
    private long id;

    @Column(name = "dt_inicial", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataInicial;

    @Column(name = "dt_final", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataFinal;

    @Column(name = "vl_total", nullable = true)
    private double valorTotal;

    @Column(name = "observacao", nullable = true)
    private String observacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Calendar dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Calendar getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Calendar dataFinal) {
        this.dataFinal = dataFinal;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
}
