package org.tutorial;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("/books")
public class BookController {

	private BookDAO bookDAO = new BookDAOImpl();
	// private BookDAO bookDAO = new BookDAOMockImpl();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	public String hello(@Context HttpServletRequest req) {
		HttpSession session = req.getSession();
		int count = session.getAttribute("count") == null ? 0 : (int) session.getAttribute("count");
		count++;
		session.setAttribute("count", count);
		return "Hello World! -> " + count;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getBooks(@Context HttpServletRequest request, @QueryParam("title") String title) {

		List<Book> books = null;
		if (title != null) {
			books = bookDAO.findByTitle(title);
		} else {
			books = bookDAO.findByAll();
		}

		useSession(request, title);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(books);
		return json;
	}

	private void useSession(HttpServletRequest request, String title) {

		HttpSession session = request.getSession();
		List<String> queries = (List<String>) session.getAttribute("queries");
		if (queries == null) {
			queries = new ArrayList<>();
			session.setAttribute("queries", queries);
		}
		queries.add(title);
		System.out.println("liste des recherches stockées en session :");
		queries.stream().forEach(x -> System.out.println("-" + x));

	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void createBook(@FormParam("book_title") String bookTitle, @FormParam("book_author") String bookAuthor) {
		Book book = new Book(0, bookTitle, bookAuthor);
		System.out.println("new book created : " + book);
	}

}
