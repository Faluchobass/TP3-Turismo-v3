package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static Connection connection;
	private static String url = "jdbc:sqlite:turismo_multiverso.db";

	public static Connection getConnection()  {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("No se pudo conectar con la base de datos");
				e.printStackTrace();
				throw new Error(e);
			}
		}
		return connection;
	}
}
