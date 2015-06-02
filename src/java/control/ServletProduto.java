/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;
import persistence.ProdutoDAO;

/**
 *
 * @author daniel
 */
public class ServletProduto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String tipo= (String)request.getParameter("tipo");
        
        if (tipo.compareTo("alteraLimite")==0)
            try {
                alteraLimite(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void alteraLimite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        Produto pro = new Produto();
        pro.setCodigo(Integer.parseInt(request.getParameter("id")));
        pro.setLimite(Integer.parseInt(request.getParameter("limite")));
        
        ProdutoDAO produtodao = new ProdutoDAO();
        produtodao.alteraLimite(pro);
        
        request.setAttribute("tipo", "carrega");
        request.setAttribute("limite", "alterado");
        RequestDispatcher rd = request.getRequestDispatcher("/ServletMain");
        rd.forward(request, response);
    }

}
