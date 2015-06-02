/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ServletMain", urlPatterns = {"/ServletMain"})
public class ServletMain extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String tipo= (String)request.getParameter("tipo");
        
        if (tipo.compareTo("carrega")==0)
            carregaMain(request, response);
        if (tipo.compareTo("tipo")==0)
            tipoCli(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String tipo= (String)request.getAttribute("tipo");
        
        if (tipo.compareTo("carrega")==0)
            carregaMain(request, response);
    }
    
    private void carregaMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ProdutoDAO produtodao = new ProdutoDAO();
        List<Produto> produtos = null;
        produtos = produtodao.buscaTodos();
        request.setAttribute("listProdutos", produtos);
        
        List<Produto> produtosV = null;
        produtos = produtodao.buscaTodosV();
        request.setAttribute("listProdutosV", produtos);
        
        LoteDAO lotedao = new LoteDAO();
        List<Lote> lotes = null;
        lotes = lotedao.listaLotes();
        request.setAttribute("listLotes", lotes);
        
        ClienteDAO clientedao = new ClienteDAO();
        List<Cliente> clientes = null;
        clientes = clientedao.buscaTodos();
        request.setAttribute("listClientes", clientes);
        
        VendaDAO vendadao = new VendaDAO();
        List<Venda> vendas = null;
        vendas = vendadao.buscaTodos();
        request.setAttribute("listVendas", vendas);
        
        if(request.getAttribute("limite") != null){
            request.setAttribute("limite", "alterado");
        }else{
            request.setAttribute("limite", "nao");
        }
        
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/main.jsp");
        rd.forward(request, response);
    }
    
    private void tipoCli(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Cliente cli = new Cliente();
        cli.setCodigo(Integer.parseInt(request.getParameter("codigo")));
        
        ClienteDAO clientedao = new ClienteDAO();
        int res = clientedao.buscaTipo(cli.getCodigo());
        
        PrintWriter writer = response.getWriter();
        writer.print(res);
        writer.close();
    }
}
