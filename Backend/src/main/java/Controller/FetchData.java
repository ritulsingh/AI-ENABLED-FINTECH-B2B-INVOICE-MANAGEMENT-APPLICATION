package Controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Controller.CRUD;
/**
 * Servlet implementation class UserProfile
 */
@WebServlet("/")

    /**
     * @see HttpServlet#HttpServlet()
     */
    
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */


public class FetchData extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","password12");
			Statement st = conn.createStatement();
			String query ="SELECT dataId,business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,document_create_date,document_create_date_1,due_in_date,invoice_currency,document_type,posting_id,area_business,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id,isOpen FROM sakila.data LIMIT 50";
			ResultSet result = st.executeQuery(query);
			ArrayList<CRUD> data = new ArrayList<CRUD>();
	
			while(result.next()) {
				CRUD ClassDetails = new CRUD();
				ClassDetails.setSl_no(result.getInt("dataId")); 
				ClassDetails.setBusiness_code(result.getString("business_code"));
				ClassDetails.setCust_number(result.getString("cust_number"));
				ClassDetails.setClear_date(result.getString("clear_date"));
				ClassDetails.setBuisness_year(result.getString("buisness_year"));
				ClassDetails.setDoc_id(result.getString("doc_id"));
				ClassDetails.setPosting_date(result.getString("posting_date"));
				ClassDetails.setDocument_create_date(result.getString("document_create_date"));
				ClassDetails.setDocument_create_date1(result.getString("document_create_date_1"));
				ClassDetails.setDue_in_date(result.getString("due_in_date"));
				ClassDetails.setInvoice_currency(result.getString("invoice_currency"));
				ClassDetails.setDocument_type(result.getString("document_type"));
				ClassDetails.setPosting_id(result.getString("posting_id"));
				ClassDetails.setArea_business(result.getString("area_business"));
				ClassDetails.setTotal_open_amount(result.getString("total_open_amount"));
				ClassDetails.setBaseline_create_date(result.getString("baseline_create_date"));
				ClassDetails.setCust_payment_terms(result.getString("cust_payment_terms"));
				ClassDetails.setInvoice_id(result.getString("invoice_id"));
				ClassDetails.setIsOpen(result.getString("isOpen"));
				
				data.add(ClassDetails);
			}
			Gson gson = new GsonBuilder().serializeNulls().create();
			String invoices  = gson.toJson(data);
			response.setHeader("Access-Control-Allow-Origin","*");
			response.setContentType("application/json");
			try {
				response.getWriter().write(invoices);//getWriter() returns a PrintWriter object that can send character text to the client. 
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			result.close();
			st.close();	
		} 
    	 catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);  
		}
	}}