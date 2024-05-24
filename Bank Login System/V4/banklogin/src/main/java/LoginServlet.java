import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/BankLoginSystem";
    private static final String USER = "root";
    private static final String PASS = "Kalampoki-2003";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("userName");
        String pass = request.getParameter("userPass");

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            String sql = "SELECT firstname, lastname, gender, balance FROM info WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            ResultSet rs = pstmt.executeQuery();

            // Extract data from result set
            if (rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String gender = rs.getString("gender");
                double balance = rs.getDouble("balance");

               printHTML(firstName,lastName,gender,balance,out);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
                rd.include(request, response);
            }

            // Clean-up environment
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void printHTML(String firstName, String lastName, String gender, double balance, PrintWriter out) {
        out.print("<html>");
        out.print("<head>");
        out.print("<title>Bank Login Page</title>");
        out.print("<meta charset=\"UTF-8\">");
        out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.print("<link rel=\"stylesheet\" href=\"main.css\">");
        out.print("</head>");
        out.print("<body>");
        out.print("<h1>Account Information</h1>");
        out.print("<label for=\"firstname\" style=\"display: inline-block; width: 100px;\">FirstName:</label>");
        out.print("<input id=\"firstname\" type=\"text\" value=\"" + firstName + "\" readonly><br>");
        out.print("<label for=\"lastname\" style=\"display: inline-block; width: 100px;\">Last Name:</label>");
        out.print("<input id=\"lastname\" type=\"text\" value=\"" + lastName + "\" readonly><br>");
        out.print("<label for=\"gender\" style=\"display: inline-block; width: 100px;\">Gender:</label>");
        out.print("<input id=\"gender\" type=\"text\" value=\"" + gender + "\" readonly><br>");
        out.print("<label for=\"balance\" style=\"display: inline-block; width: 100px;\">Balance:</label>");
        out.print("<input id=\"balance\" type=\"text\" value=\"" + balance + "\" readonly><br>");
        out.print("</body></html>");
    }
}