/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import persistence.*;

/**
 *
 * @author daniel
 */
@WebServlet(name = "ServletLote", urlPatterns = {"/ServletLote"})
public class ServletLote extends HttpServlet {
     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String tipo = request.getParameter("tipo");
        
        if(tipo.equals("add")){
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            addLotes(request, response, codigo, quantidade);            
        }
        if(tipo.equals("addM")){
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            addLoteM(request, response, codigo);            
        } 
    }

    private void addLotes(HttpServletRequest request, HttpServletResponse response, int codigo, int quantidade) throws IOException{
        
        List<Lote> lotes = null;
        LoteDAO lotedao = new LoteDAO();
        int qtde = quantidade;
        String resultado = null;
        
        lotes = lotedao.buscaLotesProduto(codigo);
        
        for (Iterator iterator = lotes.iterator(); iterator.hasNext();) {
            Lote lote = (Lote) iterator.next();
            
            if (lote.getQtde_atual() >= qtde){
                if(resultado == null)
                    resultado= "#"+lote.getCodigo()+"#"+lote.getDt_fabricacao()+"#"+lote.getDt_validade()+"#"+qtde;
                else
                    resultado+= "#"+lote.getCodigo()+"#"+lote.getDt_fabricacao()+"#"+lote.getDt_validade()+"#"+qtde;
                break;
            } else {
                if(resultado == null)
                    resultado= "#"+lote.getCodigo()+"#"+lote.getDt_fabricacao()+"#"+lote.getDt_validade()+"#"+lote.getQtde_atual();
                else
                    resultado+= "#"+lote.getCodigo()+"#"+lote.getDt_fabricacao()+"#"+lote.getDt_validade()+"#"+lote.getQtde_atual();
                qtde-=lote.getQtde_atual();
            }
        }
        
        PrintWriter writer = response.getWriter();
        writer.print(resultado);
        writer.close();
    }
    
    private void addLoteM(HttpServletRequest request, HttpServletResponse response, int codigo) throws IOException{
        
        Lote lote = new Lote();
        LoteDAO lotedao = new LoteDAO();
        String resultado = null;
        
        lote = lotedao.buscaLote(codigo);
        
        resultado= ""+lote.getDt_fabricacao();
        
        PrintWriter writer = response.getWriter();
        writer.print(resultado);
        writer.close();
    }
    
    public void busca(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, DAOException{

       String codigo = request.getParameter("codigo");
       String codigo_produto = request.getParameter("codigo_produto");
       String dt_validade= request.getParameter("dt_validade");
       String dt_fabricacao = request.getParameter("dt_fabricacao");
       String qtde_inicial = request.getParameter("qtde_inicial");
       String qtde_atual = request.getParameter("qtde_atual");
       LoteDAO lotedao = new LoteDAO();
       List<Lote> lotes = null;
       request.setAttribute("listLotes", lotes);
       
        RequestDispatcher rd = request.getRequestDispatcher("/viewLotes.jsp");
	rd.forward(request, response);
    }
}
