package connection;
import java.sql.*;

public class DBConnection {

	public static Connection connect() {
		Connection conn = null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8111/project", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

}
