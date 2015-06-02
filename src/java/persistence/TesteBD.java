package persistence;
import java.sql.*;

public class TesteBD {
//Objeto que guarda informacoes da conexao com o SGBD.
    private Connection conn;

    //Objeto usado para enviar comandos SQL no SGBD
    private Statement stmt;


	public TesteBD(){

     try{
   Class.forName("org.postgresql.Driver");
    	  String conexao="jdbc:postgresql://localhost/ERPArrayEnterprises";
         String usuario="postgres", senha="root";
           conn = DriverManager.getConnection(conexao,usuario,senha);
           stmt = conn.createStatement();

           conn.close();
     }
     catch (Exception e){
         e.printStackTrace();
         System.out.println("Erro");
     }

        }


public static void main (String args[]){
  TesteBD t=new TesteBD();
}
}