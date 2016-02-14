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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author ANDRE
 */
@ManagedBean
@ViewScoped
public class ItemMB {

    FacesMessage msg = null;
    private ItemDao itemDao;
    private Item item;
    private static List<Item> listaItens;

    @PostConstruct
    public void init() {
        item = new Item();
        itemDao = new ItemDao();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getListaItens() {
        if (listaItens == null) {
            listaItens = itemDao.getAll();
        }
        return listaItens;
    }

    public void setListaItens(List<Item> listaItem) {
        this.listaItens = listaItem;
    }

    public void onEditarLinha(RowEditEvent event) {
        Item itemEditado = (Item) event.getObject();
        try {
            itemDao.save(itemEditado);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item editado", (String.valueOf((itemEditado.getId()))));
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro na edição, tente mais tarde.", (String.valueOf((itemEditado.getId()))));
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancelarEdicaoLinha(RowEditEvent event) {
        Item itemEditado = (Item) event.getObject();
        msg = new FacesMessage("Edição cancelada", (String.valueOf((itemEditado.getId()))));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void salvarItem() {
        try {
            itemDao.save(item);
            item = new Item();
            msg = new FacesMessage("Cadastro", "Item cadastrado com sucesso");
            this.setListaItens(null);
            this.getListaItens();
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro no cadastro, tente mais tarde.", null);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String reiniciarPanel() {
        item = new Item();
        return null;
    }

    public void excluirItem(Item i) {
        try {
            if (listaItens.contains(i)) {
                itemDao.delete(i);
                listaItens.remove(i);
                item = new Item();
                msg = new FacesMessage("Exclusão", "Item excluído com sucesso");
            }
        } catch (Exception e) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ops, ocorreu algum erro no cadastro, tente mais tarde.", null);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
