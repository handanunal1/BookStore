package com.pluralsight;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
/* @WebServlet("/ControllerServlet") */
@WebServlet("/books/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<Book> bookList = new ArrayList<Book>();
	private BookDAO bookDAO;

	/**
	 * @throws SQLException 
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerServlet() throws SQLException {
		super();
		
		bookDAO = new BookDAO();
		bookDAO.connect();
		bookDAO.disconnect();
		
	

//		bookList.add(new Book("To Kill a Mockingbird", "Harper lee", 5.50f));
//		bookList.add(new Book("1984", "George Orwell", 4.5f));
//		bookList.add(new Book("Frankestain", "Mary Shelly", 4.00f));

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getPathInfo();
		if (action.equals("/new")) {
			addBook(request, response);

		}

		else {
			listBooks(request, response);
		}
	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			ArrayList<Book> books = bookDAO.listAllBooks();
			request.setAttribute("book_list", books);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BookForm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getPathInfo();
		if (action.equals("/insert")) {
			insertBook(request, response);

		}
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String title = request.getParameter("booktitle");
		String author = request.getParameter("bookauthor");
		String price = request.getParameter("bookprice");
		
		Book newBook = new Book(title,author,Float.parseFloat(price));
		bookList.add(newBook);
		
		response.sendRedirect("list");
	}
}
