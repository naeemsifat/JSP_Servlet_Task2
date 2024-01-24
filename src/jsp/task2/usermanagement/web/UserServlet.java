package jsp.task2.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.task2.usermanagement.dao.CustomerDao;
import jsp.task2.usermanagement.model.Customer;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDao customerDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	customerDAO = new CustomerDao();
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Customer> listUser = customerDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Customer existingUser = customerDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		// Ensuring that the request parameters are not null before parsing
        Double knit_card_balance = parseDouble(request.getParameter("knitCardBalance"));
        Integer knit_card_number = parseInteger(request.getParameter("knitCardNumber"));
        Double knit_card_quantity = parseDouble(request.getParameter("knitCardQuantity"));
        Double requisition_quantity = parseDouble(request.getParameter("requisitionQuantity"));
        Integer trnumber = parseInteger(request.getParameter("trNumber"));
		
		Customer customer = new Customer(trnumber, knit_card_number, requisition_quantity, knit_card_quantity, knit_card_balance);
		customerDAO.insertUser(customer);
		response.sendRedirect("list");
		
	}

	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
	        throws SQLException, IOException {
	    try {
	        Long id = Long.parseLong(request.getParameter("id"));

	        // Ensuring that the request parameters are not null before parsing
	        Double knit_card_balance = parseDouble(request.getParameter("knitCardBalance"));
	        Integer knit_card_number = parseInteger(request.getParameter("knitCardNumber"));
	        Double knit_card_quantity = parseDouble(request.getParameter("knitCardQuantity"));
	        Double requisition_quantity = parseDouble(request.getParameter("requisitionQuantity"));
	        Integer trnumber = parseInteger(request.getParameter("trNumber"));

	        Customer customer = new Customer(id, trnumber, knit_card_number, requisition_quantity, knit_card_quantity, knit_card_balance);
	        customerDAO.updateUser(customer);
	        response.sendRedirect("list");
	    } catch (NumberFormatException | NullPointerException e) {
	        // Handle parsing errors or null values if needed
	        e.printStackTrace();  // Log the exception for debugging
	        response.sendRedirect("error.jsp");  // Redirect to an error page
	    }
	}
	
	private Double parseDouble(String value) { //This method is for parse the non null or non empty value
	    if (value != null && !value.isEmpty()) {
	        try {
	            return Double.parseDouble(value);
	        } catch (NumberFormatException e) {
	            // Handle parsing error if needed
	            e.printStackTrace();  // Log the exception for debugging
	        }
	    }
	    return null;
	}
	
	private Integer parseInteger(String value) { //This method is for parse the non null or non empty value
	    if (value != null && !value.isEmpty()) {
	        try {
	            return Integer.parseInt(value);
	        } catch (NumberFormatException e) {
	            // Handle parsing error if needed
	            e.printStackTrace();  // Log the exception for debugging
	        }
	    }
	    return null;
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		customerDAO.deleteUser(id);
		response.sendRedirect("list");

	}


}
