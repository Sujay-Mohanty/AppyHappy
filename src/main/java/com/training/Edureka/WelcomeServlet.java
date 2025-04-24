package com.training.Edureka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	PrintWriter out=response.getWriter();
    	out.println("Hello");
    	String place=request.getParameter("place");
    	String city=request.getParameter("city");
    	out.println(city+" "+place);
    	
//    	String username=request.getParameter("username");
//    	String password=request.getParameter("password");
//    	out.println(password+" "+username);
    }
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
////		response.getWriter().append("Served at: ").append(request.getContextPath());
//		PrintWriter out=response.getWriter();
//		response.setContentType("text/html");
//		for(int i=1;i<=5;i++) {
//			out.println("<h"+i+"> Hello from Welcome Servlet"+i+"</h"+i+">");
//		}
//		out.println("<h2> Its "+Instant.now()+"@server..</h2>");
//		
//		SimpleDateFormat sd = new SimpleDateFormat(
//	            "yyyy.MM.dd G 'at' HH:mm:ss z");
//	        Date date = new Date();
//	        // TODO: Avoid using the abbreviations when fetching time zones.
//	        // Use the full Olson zone ID instead.
//	        sd.setTimeZone(TimeZone.getTimeZone("IST"));
//		out.println("<h2> Its "+ sd.format(date)+"@server..</h2>");
//		
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
    	PrintWriter out=response.getWriter();
    	String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	out.println(password+" "+username);
    	
    	ServletContext context = getServletContext();
    	String dbUrl_context = context.getInitParameter("dburl");
    	ServletConfig config=getServletConfig();
    	String dbUrl = config.getInitParameter("url");
    	String dbUser = config.getInitParameter("dbuser");
    	String dbPwd = config.getInitParameter("dbpwd");
    	out.println(dbUrl+" "+dbUser+ " "+dbPwd);
    	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbUrl_context, dbUser,dbPwd);
			System.out.println("Connected hehe");
			PreparedStatement ps=con.prepareStatement("insert into appuser values(?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			int count=ps.executeUpdate(); //for dml operations
			
			if(count>=1)
				{out.println("<h1> Welcome to our World! Dear "+ username +"</h1>");
			
			PreparedStatement ps1=con.prepareStatement("select username from appuser");
			java.sql.ResultSet rs=ps1.executeQuery(); //for dml operations
			java.util.List<String> userList=new java.util.ArrayList<String>();
			while(rs.next()) {
				userList.add(rs.getString(1));
			}
			out.println("<h1>List of Users "+userList +"</h1>");}
			else
				out.println("<h1> Something went wrong Bruh </h1>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
