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
import java.util.List;
import model.Produto;

/**
 *
 * @author daniel
 */
public class ProdutoDAO {
    
    private Connection c = ConnectionFactory.getConexao();

    public List<Produto> buscaTodos() {
        String sql = "SELECT * FROM produto ORDER BY nome";
        List<Produto> lista = new ArrayList<>();
        
        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();

            while (resultado.next()) {
                Produto prod = new Produto();
                
                prod.setCodigo(resultado.getInt("codigo"));
                prod.setNome(resultado.getString("nome"));
                prod.setLimite(resultado.getInt("limite"));
                lista.add(prod);
            }
            p.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
    
    public List<Produto> buscaTodosV() {
        String sql = "select l.codigo_produto, p.nome, sum(qtde_atual) as qtdetotal, p.preco_unit as preco , p.ramo as ramo, p.limite as limite\n" +
                    "	from lote l, produto p\n" +
                    "	where l.codigo_produto = p.codigo and l.dt_validade > (current_date + p.limite)\n" +
                    "	group by l.codigo_produto, p.nome, p.preco_unit, p.ramo, p.limite\n" +
                    "	order by l.codigo_produto";
        List<Produto> lista = new ArrayList<>();
        
        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();

            while (resultado.next()) {
                Produto prod = new Produto();
                
                prod.setCodigo(resultado.getInt("codigo_produto"));
                prod.setNome(resultado.getString("nome"));
                prod.setQuantidade(Integer.parseInt(resultado.getString("qtdetotal")));
                prod.setPreco_unit(resultado.getDouble("preco"));
                prod.setRamo(resultado.getInt("ramo"));
                prod.setLimite(resultado.getInt("limite"));
                lista.add(prod);
            }
            p.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
    
    public Produto buscaCodigo(int codigo) {
        String sql = "SELECT * FROM produto WHERE codigo ="+codigo;
        Produto prod = new Produto();

        try {
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet resultado = p.executeQuery();
            prod = new Produto();
            
            while (resultado.next()) {
                prod.setCodigo(resultado.getInt("codigo"));
                prod.setNome(resultado.getString("nome"));
                prod.setPreco_unit(resultado.getDouble("preco_unit"));
                prod.setRamo(resultado.getInt("ramo"));
            }
            
            p.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return prod;
    }
    
    public void alteraLimite(Produto pro) throws SQLException{
        String sql = "UPDATE produto SET limite = "+pro.getLimite()+" WHERE codigo = "+pro.getCodigo();
        
        Statement s = c.createStatement();
        s.executeUpdate(sql);
        
        System.out.println("LALALALALALA SALVOOUUUU");
    }
}
