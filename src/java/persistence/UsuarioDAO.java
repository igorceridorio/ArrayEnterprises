/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Usuario;

/**
 *
 * @author daniel
 */
public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO() throws DAOException {
        try {
            this.conn = ConnectionFactory.getConexao();

        } catch (Exception e) {
            throw new DAOException("Erro:\n" + e.getMessage());
        }
    }

    public boolean isUsuarioValido(Usuario usuario, int x) throws DAOException, SQLException {

        String login = usuario.getLogin();
        String senha = usuario.getSenha();


        Usuario novo = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = this.conn;

        //FAZER BUSCA SQL AQUI

        if (login == null || senha == null) {
            throw new DAOException("O valor passado não pode ser nulo");
        }

        try {
            String SQL = "SELECT * FROM Usuario WHERE nome_usuario = ? AND senha = ?";
            ps = conn.prepareStatement(SQL);
            ps.setString( 1, login );
            ps.setString( 2, senha );
            
            rs = ps.executeQuery();

            while (rs.next()) {
                String loginb = rs.getObject("nome_usuario").toString();
                String password = rs.getObject("senha").toString();
                novo = new Usuario();
                novo.setLogin(loginb);
                novo.setSenha(password);
            }

        } catch (SQLException sqle) {
            throw new DAOException("Erro ao Logar " + sqle);
        } finally {
            ps.close();

        }
        if (x == 1) {
            if (novo != null) {
                if (((novo.getLogin()).equals(login)) && ((novo.getSenha()).equals(senha))) {
                    return true;
                }
            }
        } else {
            if (novo != null) {
                return true;
            }
        }
        return false;

    }
}
