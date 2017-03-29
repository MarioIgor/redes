/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Mariano
 */
public class FileMessage implements Serializable{
    
    private String destinatario;
    private String remetente;
    private File file;
    private String msg;
    private Action action;
    private Set<String> listaClientes;

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }
    
    public Set<String> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(Set<String> listaClientes) {
        this.listaClientes = listaClientes;
    }

    
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
    public FileMessage(String remetente, String msg, Action action) {
        this.remetente = remetente;
        this.msg = msg;
        this.action = action;
    }

    public FileMessage(String remetente, File file, Action action) {
        this.remetente = remetente;
        this.file = file;
        this.action = action;
    }
    public FileMessage(String destinatario, File file) {
        
        this.destinatario = destinatario;
        this.remetente = null;
        this.file = file;
        this.action = null;
    }
    public FileMessage(String destinatario,String remetente, File file) {
        
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.file = file;
        this.action = null;
    }
    public FileMessage(String destinatario,String remetente, File file, Action action) {
        
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.file = file;
        this.action = action;
    }
    public FileMessage(String destinatario,String remetente, String msg, Action action) {
        
        this.destinatario = destinatario;
        this.remetente = remetente;
        this.msg = msg;
        this.action = action;
    }

    public FileMessage(String remetente, String destinatario) {
        this.destinatario = destinatario;
        this.remetente = remetente;
    }
    public FileMessage(String remetente) {
        
        this.remetente = remetente;
        this.destinatario = null;
    }
    
    public FileMessage() {
        this.destinatario = null;
        this.remetente = null;
        this.file = null;
        this.action = null;
        
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    
}

