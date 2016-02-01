package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcTest {

  public static void main(String args[]) {

    String url = "jdbc:mysql://127.0.0.1:3306/skeleton?characterEncoding=UTF-8";
    String driver = "com.mysql.jdbc.Driver";
    Connection con = null;

    try {
      Class.forName(driver);
      con = DriverManager.getConnection(url, "skeleton", "skeleton");
      if (!con.isClosed()) System.out.println("success");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
