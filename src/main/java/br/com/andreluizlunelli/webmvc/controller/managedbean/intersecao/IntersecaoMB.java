/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.controller.managedbean.intersecao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ANDRE
 */
@ManagedBean
@ViewScoped
public class IntersecaoMB {
    FacesMessage msg = null;
    private String[] val;
    
    private int[] parseMatrizStringToInt(String[] valStr) throws MatrizInvalida {
        int[] valInt = new int[4];   
        if (valStr == null || valStr.length == 0) {
            throw new MatrizInvalida();
        }
        for (int i=0; i < valStr.length; i++) {
            valInt[i] = Integer.valueOf(valStr[i]);            
        }
        return valInt;
    }
    
    @PostConstruct
    public void init() {
        val = new String[4];
    }
    
    public void consultar() {
        try {
            int[] valInt = this.parseMatrizStringToInt(val);            
            boolean isIntersecao = CalcularIntersecao.calcular(valInt);
            if (isIntersecao) {
                msg = new FacesMessage("Existe interseção entre as faixas 1 e 2.");
            } else {
                msg = new FacesMessage("Não há interseção entre as faixas 1 e 2.");
            }
        } catch (MatrizInvalida ex) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro com a matriz.", "1");
        }        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String[] getVal() {
        return val;
    }

    public void setVal(String[] val) {
        this.val = val;
    } 
}

