package jsp.task2.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jsp.task2.usermanagement.model.Customer;

//This Dao class provide CRUD operations on the table name "sample_file" 
public class CustomerDao {
	//Database properties 
	private String jdbcURL = "jdbc:mysql://localhost:3306/customer_db?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "sifat123";
	
	private static final String INSERT_CUSTOMERS_SQL = "INSERT INTO sample_file" + "  (knit_card_balance, knit_card_number, knit_card_quantity, requisition_quantity, trnumber) VALUES "
			+ " (?, ?, ?, ?, ?);";

	private static final String SELECT_CUSTOMER_BY_ID = "SELECT knit_card_balance, knit_card_number, knit_card_quantity, requisition_quantity, trnumber FROM sample_file where sample_file.id = ?";
 
	private static final String SELECT_ALL_CUSTOMERS = "select * from sample_file";
	private static final String DELETE_CUSTOMERS_SQL = "delete from sample_file where id = ?;";
	private static final String UPDATE_CUSTOMERS_SQL = "update sample_file set knit_card_balance = ?, knit_card_number = ?, knit_card_quantity = ?, requisition_quantity = ?, trnumber = ?  where id = ?;";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertUser(Customer customer) throws SQLException {
		System.out.println(INSERT_CUSTOMERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL)) {
			preparedStatement.setObject(1, customer.getKnitCardBalance());
	        preparedStatement.setObject(2, customer.getKnitCardNumber());
	        preparedStatement.setObject(3, customer.getKnitCardQuantity());
	        preparedStatement.setObject(4, customer.getRequisitionQuantity());
	        preparedStatement.setObject(5, customer.getTrNumber());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Customer selectUser(Long id) {
		Customer customer = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
			preparedStatement.setLong(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				Double knit_card_balance = rs.getDouble("knit_card_balance");
				Integer knit_card_number = rs.getInt("knit_card_number");
				Double knit_card_quantity = rs.getDouble("knit_card_quantity");
				Double requisition_quantity = rs.getDouble("requisition_quantity");
				Integer trnumber = rs.getInt("trnumber");
				customer = new Customer(id, trnumber, knit_card_number, requisition_quantity, knit_card_quantity, knit_card_balance);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return customer;
	}

	public List<Customer> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Customer> customers = new ArrayList<>();
		//  Establishing a Connection
		try (Connection connection = getConnection();

				// Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);) {
			System.out.println(preparedStatement);
			//  Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			//  Process the ResultSet object.
			while (rs.next()) {
				Long id = rs.getLong("id");
				Double knit_card_balance = rs.getDouble("knit_card_balance");
				Integer knit_card_number = rs.getInt("knit_card_number");
				Double knit_card_quantity = rs.getDouble("knit_card_quantity");
				Double requisition_quantity = rs.getDouble("requisition_quantity");
				Integer trnumber = rs.getInt("trnumber");
				customers.add(new Customer(id, trnumber, knit_card_number, requisition_quantity, knit_card_quantity, knit_card_balance));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return customers;
	}

	public boolean deleteUser(Long id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMERS_SQL);) {
			statement.setLong(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(Customer customer) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMERS_SQL);) {
			
			preparedStatement.setObject(1, customer.getKnitCardBalance());
	        preparedStatement.setObject(2, customer.getKnitCardNumber());
	        preparedStatement.setObject(3, customer.getKnitCardQuantity());
	        preparedStatement.setObject(4, customer.getRequisitionQuantity());
	        preparedStatement.setObject(5, customer.getTrNumber());
	        preparedStatement.setLong(6, customer.getId());

	        System.out.println("Executing SQL Update: " + preparedStatement); // Log the SQL statement

	        rowUpdated = preparedStatement.executeUpdate() > 0;
	        System.out.println("Rows updated: " + (rowUpdated ? "Yes" : "No")); // Log whether rows were updated
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {  // Throw and print exception
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
