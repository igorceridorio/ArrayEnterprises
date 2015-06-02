/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author daniel
 */
public class Cliente {
    
    private int codigo;
    
    private String nome;
    
    private String ramo;
    
    private String esp_ramo;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getEsp_ramo() {
        return esp_ramo;
    }

    public void setEsp_ramo(String esp_ramo) {
        this.esp_ramo = esp_ramo;
    }   
}
