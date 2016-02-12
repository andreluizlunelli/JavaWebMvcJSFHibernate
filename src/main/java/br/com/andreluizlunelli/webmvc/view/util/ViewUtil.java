/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.view.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ANDRE
 */
public class ViewUtil {

    public static String dateToStr(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(d);
    }
}
