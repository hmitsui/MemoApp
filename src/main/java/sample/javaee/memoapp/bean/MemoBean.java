/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.memoapp.bean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author
 */
@Named(value = "memoBean")
@ViewScoped
public class MemoBean {

    /**
     * Creates a new instance of MemoBean
     */
    public MemoBean() {
    }
    
}
