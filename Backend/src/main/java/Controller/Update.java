package Controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Controller.CRUD;
@WebServlet("/Update")

public class Update extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    			PrintWriter out = response.getWriter();
   			
    			response.setContentType("application/json");
    			try {
    				CRUD data = new Gson().fromJson(request.getReader(), CRUD .class);

    				Class.forName("com.mysql.cj.jdbc.Driver");
    				 
    				Connection con = DriverManager.getConnection(  
    					"jdbc:mysql://localhost:3306/sakila","root","password12");
    				
    				String query = 
    						"UPDATE data set invoice_currency = ?,cust_payment_terms = ? where dataId = ?";
    				PreparedStatement st = con.prepareStatement(query);

    				st.setString (1,data.getInvoice_currency());
    				st.setString (2, data.getCust_payment_terms());
    				st.setInt(3, data.getSl_no());
    				
    				if(st.executeUpdate() > 0) {
    					System.out.println("done");
    				} else {
    					System.out.println("no");
    				}			
    				con.close();		
    				;
    			} catch (Exception e) {
    				System.out.println(e);
    			}	
    }
}