package pseudotests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.epam.lab.intouch.dao.exception.DBConnectionException;
import com.epam.lab.intouch.dao.util.ConnectionManager;

public class DummyConnectionTester {

	private static void displayData(final ResultSet rs) throws SQLException {
		StringBuilder builder = new StringBuilder();

		while (rs.next()) {
			builder.append("email: ").append(rs.getString("login")).append(", ");
			builder.append("name: ").append(rs.getString("name")).append(", ");
			builder.append("surname: ").append(rs.getString("surname"));

			System.out.println(builder.toString());
			builder.setLength(0);
		}
	}

	public static void main(String[] args) {

		ConnectionManager manager = ConnectionManager.getInstance();

		try (Connection connection = manager.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM member");

		) {

			displayData(rs);

		} catch (DBConnectionException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
