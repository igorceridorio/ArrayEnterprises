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
public class Produto {    
    
    private int codigo;
    private String nome;
    private int quantidade;
    private Double preco_unit;
    private int ramo; // 1=alimenticio  2=medicamentos
    private int limite; // limite de dias para a validade, utilizado na venda automatica
    
    //metodos
    
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco_unit() {
        return preco_unit;
    }

    public void setPreco_unit(Double preco_unit) {
        this.preco_unit = preco_unit;
    }

    public int getRamo() {
        return ramo;
    }

    public void setRamo(int ramo) {
        this.ramo = ramo;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
}
