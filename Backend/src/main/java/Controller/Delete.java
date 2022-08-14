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

@WebServlet("/Delete")

public class Delete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

    PrintWriter out = response.getWriter();

	try {

		String sl_noo = request.getParameter("FIELD1");
		int sl_no = Integer.parseInt(sl_noo);
		System.out.println(sl_no);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","root","password12");
		

		PreparedStatement ps = conn.prepareStatement("delete from sakila.data where dataId=?");
		ps.setInt(1, sl_no);
		if(ps.executeUpdate() > 0){
			System.out.println("Done");
			
			
		} else {
			System.out.println("notdone");
			
		}			
//		conn.close();		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

}