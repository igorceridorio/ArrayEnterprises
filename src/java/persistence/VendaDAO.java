/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.*;

/**
 *
 * @author hitalo
 */
public class VendaDAO {

    private Connection c = ConnectionFactory.getConexao();

    public List<Venda> buscaTodos() {
        List<Venda> lista = new ArrayList<>();
        List<Lote> lotes = new ArrayList<>();
        LoteDAO lotedao = new LoteDAO();
        
        String sql = "select c.codigo as CodigoCliente, c.nome as NomeCliente, c.ramo, c.esp_ramo, v.codigo as CodigoVenda, v.preco_total as Valor from cliente c, venda v where c.codigo = v.codigo_cliente;";

        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();            
            ResultSet resultado2 = null;
            
            int i = 0;
            while (resultado.next()) {
                Venda v = new Venda();
                Cliente cli = new Cliente();
                
                v.setCodigo(resultado.getInt("codigovenda"));
                
                cli.setCodigo(resultado.getInt("codigocliente"));
                cli.setNome(resultado.getString("nomecliente"));
                cli.setRamo(resultado.getString("ramo"));
                cli.setEsp_ramo(resultado.getString("esp_ramo"));                
                v.setCliente(cli);
                
                v.setLotes(lotedao.buscaLotesVenda(v.getCodigo()));
                lista.add(v);
                
                v.setPreco_total(resultado.getDouble("valor"));

            }
            p.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public Venda buscaID(int codigo) {
        Venda v = new Venda();
        Cliente cli = new Cliente();
        List<Lote> lotes = new ArrayList<>();
        LoteDAO lotedao = new LoteDAO();
        
        String sql = "select c.codigo as CodigoCliente, c.nome as NomeCliente, c.ramo, c.esp_ramo, v.codigo as CodigoVenda, v.preco_total as Valor from cliente c, venda v where c.codigo = v.codigo_cliente AND v.codigo =" + codigo;

        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();            
            ResultSet resultado2 = null;
            
            int i = 0;
            while (resultado.next()) {                
                v.setCodigo(resultado.getInt("codigovenda"));
                
                cli.setCodigo(resultado.getInt("codigocliente"));
                cli.setNome(resultado.getString("nomecliente"));
                cli.setRamo(resultado.getString("ramo"));
                cli.setEsp_ramo(resultado.getString("esp_ramo"));                
                v.setCliente(cli);
                
                v.setLotes(lotedao.buscaLotesVenda(v.getCodigo()));
                
                v.setPreco_total(resultado.getDouble("valor"));
            }
            p.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return v;
    }
    
    public int salva(Venda venda) {
        Cliente cli = venda.getCliente();
        List<Lote> lotes = venda.getLotes();
        int codigo_venda = 0;
        
        try {            
            String sql = "SELECT max(codigo) FROM venda"; 
            PreparedStatement p = c.prepareStatement(sql);
            Statement ps = c.createStatement();
            ResultSet resultado = p.executeQuery();
            resultado.next();
            
            String cd = resultado.getString("max");
            if(cd == null)
                codigo_venda = 1;
            else 
                codigo_venda = Integer.parseInt(cd)+1;
            
            sql = "INSERT INTO venda VALUES ("+codigo_venda+","+cli.getCodigo()+","+venda.getPreco_total()+")";
            ps.executeUpdate(sql);
            
            sql = "INSERT INTO produtos_venda VALUES ";
            for (Iterator iterator = lotes.iterator(); iterator.hasNext();) {
                Lote lote = (Lote)iterator.next();
                sql = sql + "("+codigo_venda+","+lote.getCodigo()+","+lote.getQtde_pedido()+")";
                if (iterator.hasNext())
                    sql = sql + ",";
            }
            ps.executeUpdate(sql);
            
            for (Iterator iterator = lotes.iterator(); iterator.hasNext();) {
                Lote lote = (Lote)iterator.next();
                sql = "UPDATE lote SET qtde_atual = qtde_atual-"+lote.getQtde_pedido()+" WHERE codigo = "+lote.getCodigo();
                ps.executeUpdate(sql);
            }     
 
            p.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("falha na venda");
        }
        return codigo_venda;
    }
}
