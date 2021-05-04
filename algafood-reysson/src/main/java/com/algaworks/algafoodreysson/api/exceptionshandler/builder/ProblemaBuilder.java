/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.algafoodreysson.api.exceptionshandler.builder;

import com.algaworks.algafoodreysson.api.exceptionshandler.Field;
import com.algaworks.algafoodreysson.api.exceptionshandler.Problem;
import java.util.List;


/**
 *
 * @author reysson
 */
public class ProblemaBuilder {

    private Problem problema;

    public ProblemaBuilder() {
        this.problema = new Problem();
    }

    public ProblemaBuilder comStatus(Integer status) {
        this.problema.setStatus(status);
        return this;
    }

    public ProblemaBuilder comType(String type) {
        this.problema.setType(type);
        return this;
    }
    
    public ProblemaBuilder comTitle(String title){
        this.problema.setTitle(title);
        return this;
    }
    
    public ProblemaBuilder comDatail(String datail){
        this.problema.setDatail(datail);
        return this;
    }
    
    public ProblemaBuilder comField(List<Field> fields){
        problema.setField(fields);
        return this;
    }
    
    public Problem build(){
        return this.problema;
    }

}
