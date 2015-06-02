/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import persistence.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author daniel
 */
public class ValidaLogin extends HttpServlet {


     /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws java.sql.SQLException
     */
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo=request.getParameter("tipo");
        if (tipo.compareTo("login")==0)
            try {
                login(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(ValidaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        else if (tipo.compareTo("logout")==0)
            logout(request,response);
    }

    //logout
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);//pega uma sessao que ja existe
        session.invalidate(); //invalida sessao
        response.sendRedirect("index.jsp");
    }

    //login
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
       //capturar o login
       Usuario objUsuario = new Usuario();
       objUsuario.setLogin(request.getParameter("login"));
       objUsuario.setSenha(request.getParameter("senha"));

         try {
             //checa se o usuario e valido, se for cria session
             UsuarioDAO usuario = new UsuarioDAO();
             
             HttpSession session = request.getSession();//cria uma sessao(se tiver outra ele destroi)
                 //UsuarioBean user = new UsuarioBean();        
                 //user.setEmail(login);               
             
             if(usuario.isUsuarioValido(objUsuario,1)){
                 session.setAttribute("Usuario",objUsuario);
                 request.setAttribute("tipo", "carrega");
                 RequestDispatcher rd = request.getRequestDispatcher("/ServletMain");
                 rd.forward(request, response);
       
             }else{
                 objUsuario.setAutorizado(false);
                 session.setAttribute("Usuario",objUsuario);
                 response.sendRedirect("index.jsp");                

             }
         } catch (DAOException ex) {
             Logger.getLogger(ValidaLogin.class.getName()).log(Level.SEVERE, null, ex);
         }
   }
}
