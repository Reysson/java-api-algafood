/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.algafoodreysson.api.exceptionshandler.builder;

import com.algaworks.algafoodreysson.api.exceptionshandler.Field;

/**
 *
 * @author reysson
 */
public class FieldBuilder {
    
    private Field field;

    public FieldBuilder() {
        this.field = new Field();
    }
    
    public FieldBuilder comName(String name){
        this.field.setName(name);
        return this;
    }
    
    public FieldBuilder comMensagem(String message){
        this.field.setUserMessage(message);
        return this;
    }
    
    public Field build(){
        return field;
    }
    

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
    
    
}
