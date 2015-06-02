/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import persistence.VendaDAO;

/**
 *
 * @author daniel
 */
@WebServlet(name = "ServletVendas", urlPatterns = {"/ServletVendas"})
public class ServletVendas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        
        if(tipo.equals("visualiza")){
            int codigo = Integer.parseInt(request.getParameter("cod"));
            visualizaVenda(request, response, codigo);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        
        if(tipo.equals("salva")){
            int cliente = Integer.parseInt(request.getParameter("cliente"));
            String lotes = request.getParameter("lotes");
            String quantidade = request.getParameter("quantidade");
            Double total = Double.parseDouble(request.getParameter("total"));
            salvaVenda(request, response, cliente, lotes, quantidade, total);
        }
    }
    
    private void visualizaVenda(HttpServletRequest request, HttpServletResponse response, int codigo) throws ServletException, IOException{
        
        Venda venda = new Venda();
        VendaDAO vendadao = new VendaDAO();
        
        venda = vendadao.buscaID(codigo);
        request.setAttribute("venda", venda);
        
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/viewVenda.jsp");
        rd.forward(request, response);
    }
    
    private void salvaVenda(HttpServletRequest request, HttpServletResponse response, int cli, String lot, String quantidade, Double total) throws ServletException, IOException{
        
        Venda venda = new Venda();
        Cliente cliente = new Cliente();
        List<Lote> lotes = new ArrayList<Lote>();
        VendaDAO vendadao = new VendaDAO();
        
        cliente.setCodigo(cli);
        venda.setCliente(cliente);
        
        String plotes[] = lot.split("#");
        String pqtdes[] = quantidade.split("#");
        
        for(int i = 0; i < plotes.length; i++){
            Lote lote = new Lote();
            lote.setCodigo(Integer.parseInt(plotes[i]));
            lote.setQtde_pedido(Integer.parseInt(pqtdes[i]));
            lotes.add(lote);
        }        
        venda.setLotes(lotes);
        venda.setPreco_total(total);
        
        int cod_venda = vendadao.salva(venda);
        
        RequestDispatcher rd = null;
        
        String red = "ServletVendas?tipo=visualiza&cod="+cod_venda;
        response.sendRedirect(red);
    }
}
