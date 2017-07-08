package com.homesic.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
 
@Named
@ViewScoped
public class CounterView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int number = 0;
 
    public int getNumber() {
        return number;
    }
 
    public void increment() {
        number++;
    }
}