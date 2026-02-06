import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection1 {
    public static void main(String[] args) {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
            String url = "jdbc:mysql://localhost:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false";
            String username = "testuser";
            String password = "password";

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");

            Statement stmt = conn.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS user (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), email VARCHAR(50))";
            stmt.executeUpdate(createTableQuery);
            System.out.println("Table 'users' created successfully!");
            String insertQuery = "INSERT INTO users (name, email) VALUES ('Haritha', 'haritha.doe@example.com'),('roopa', 'roopa.doe@example.com'),('ammu', 'ammu.doe@example.com')";
            
	   stmt.executeUpdate(insertQuery);
	   System.out.println("Record inserted successfully!");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

