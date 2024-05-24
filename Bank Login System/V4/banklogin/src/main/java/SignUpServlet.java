import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class SignUpServlet extends HttpServlet{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/BankLoginSystem";
    private static final String USER = "root";
    private static final String PASS = "Kalampoki-2003";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        double balance = 0;

        out.println("<html>");
        out.print("<head>");
        out.print("<title>Bank Login Page</title>");
        out.print("<meta charset=\"UTF-8\">");
        out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.print("<link rel=\"stylesheet\" href=\"main.css\">");
        out.print("</head>");
        out.print("<body>");
        out.println("<h1>Sign Up Succesful!</h1>");
        out.print("</body></html>");
        out.print("<html><body>");
        out.print("user: " + user + "<br>");
        out.print("pass: " + pass + "<br>");
        out.print("fname: " + fname + "<br>");
        out.print("lname: " + lname + "<br>");
        out.print("Gender: " + gender + "<br>");
        out.print("Balance: " + balance + "<br>");
        out.print("</body></html>");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sqlInsert = "INSERT INTO INFO VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pStatement = conn.prepareStatement(sqlInsert);

            // Set the values for the placeholders (?)
            pStatement.setString(1, user);
            pStatement.setString(2, fname);
            pStatement.setString(3, lname);
            pStatement.setString(4, pass);
            pStatement.setDouble(5, balance);
            pStatement.setString(6, gender);

            pStatement.executeUpdate();

            pStatement.close();
            conn.close();
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } 

    }
}