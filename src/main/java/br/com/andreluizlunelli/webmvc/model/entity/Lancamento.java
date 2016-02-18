
package br.com.andreluizlunelli.webmvc.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author ANDRE
 */
@Entity
@Table(name = "lancamento")
@NamedQueries({
    @NamedQuery(name = "Lancamento.findAll", query = "SELECT c FROM Lancamento c"),
    @NamedQuery(name = "Lancamento.find", query = "SELECT i FROM Lancamento i WHERE i.id = :id")
})
public class Lancamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oid")
    private long id;

    @Column(name = "dt_inicial", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicial;

    @Column(name = "dt_final", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinal;

    @Column(name = "vl_total", nullable = true)
    private double valorTotal;

    @Column(name = "observacao", nullable = true)
    private String observacao;
            
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "lancamento_item"        
            , indexes = {
                @Index(name = "fk_lancamento_item_lancamento_idx"
                    , columnList = "lancamento_oid"
                    , unique = false)
                , @Index(name = "fk_lancamento_item_item1_idx"
                    , columnList = "item_oid"
                    , unique = false)
            }
            , joinColumns = @JoinColumn(name = "lancamento_oid"                    
                    , referencedColumnName = "oid")
            , inverseJoinColumns = @JoinColumn(name = "item_oid"                    
                    , referencedColumnName = "oid")            
    )
    private List<Item> listaItens;

    public Lancamento() {
        listaItens = new ArrayList<>(); 
        valorTotal = 0;
    }        

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
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

    public List<Item> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<Item> listaItens) {
        this.listaItens = listaItens;
        this.valorTotal = this.calcularValorTotal(listaItens);        
    }           
    
    public void addItem(Item i) {
        this.listaItens.add(i);
        valorTotal = valorTotal + i.getValor();
    }
    
    private double calcularValorTotal(List<Item> items) {        
        double valorTotalCalculado = 0;
        if (items != null) {
            for (Item i : items) {
                double valor = i.getValor();            
                valorTotalCalculado = valorTotalCalculado + valor;
            }            
        }
        return valorTotalCalculado;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode(this.dataInicial);
        hash = 47 * hash + Objects.hashCode(this.dataFinal);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.valorTotal) ^ (Double.doubleToLongBits(this.valorTotal) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.observacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lancamento other = (Lancamento) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorTotal) != Double.doubleToLongBits(other.valorTotal)) {
            return false;
        }
        if (!Objects.equals(this.observacao, other.observacao)) {
            return false;
        }
        if (!Objects.equals(this.dataInicial, other.dataInicial)) {
            return false;
        }
        if (!Objects.equals(this.dataFinal, other.dataFinal)) {
            return false;
        }
        return true;
    }   
    
}
