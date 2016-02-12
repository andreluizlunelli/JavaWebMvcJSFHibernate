/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.model.dao;

import java.util.List;

/**
 *
 * @author ANDRE
 */
public interface Dao<T> {
    public T get(long id);
    public List<T> getAll();
    public void save(T t);
    public void update(T t);
    public void delete(T t);
}
