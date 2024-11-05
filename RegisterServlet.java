import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.sql.*;
import javax.servlet.http.*;


public class RegisterServlet extends HttpServlet
{
  public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
  {
      response.setContentType("text/html");
      PrintWriter out=response.getWriter();
      try
      {
          String user=request.getParameter("UserName");
          String pwd=request.getParameter("Password");
          String mble=request.getParameter("mobile");
          String email=request.getParameter("email");
          String Aid=request.getParameter("Aadhar");
          String Add=request.getParameter("Address");
          Class.forName("com.mysql.jdbc.Driver");
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/nit","root","root");
          PreparedStatement ps=con.prepareStatement("insert into data values(?,?,?,?,?,?)");
          ps.setString(1,user);
          ps.setString(2,pwd);
          ps.setString(3,mble);
          ps.setString(4,email);
          ps.setString(5,Aid);
          ps.setString(6,Add);
          int result=ps.executeUpdate();
          if(result==1){
              out.println("<h3 align='Center'><font color='green'><b>Your Account is Created Successfully!</font></h3>");
              RequestDispatcher rd=request.getRequestDispatcher("index.html");
              rd.include(request,response);
          }
          else
          {
             out.println("<h3 align='Center'><font color='red'><b>Try Again!</font></h3>"); 
             RequestDispatcher rd=request.getRequestDispatcher("index.html");
             rd.include(request, response);
          }
          ps.close();
          con.close();
      
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
          
        }
      
    } 
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
    {
      doGet(request,response);
    }  
}
