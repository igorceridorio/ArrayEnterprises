/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author daniel
 */
public class Usuario {

    private String senha;
    private String login;
    private boolean autorizado;
    /* 
     *  *  private String login;
     * public String getLogin() {
     return login;
     }
   

     public void setLogin(String login) {
     this.login = login;
     } */

    public Usuario() {
    }

    public Usuario(String senha, String login) {
        this.login = login;
        this.senha = senha;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public boolean getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }
}

