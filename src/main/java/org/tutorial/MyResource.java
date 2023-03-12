package org.tutorial;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	public String hello() {

		return "Hello World!";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/books")
	public String getBooks() {
		Book book = new Book(1, "Tolkien", "Le Seigneur des Anneaux");
		Book book2 = new Book(2, "Beigbeder", "99F");
		List<Book> books = List.of(book, book2);
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();
		final String json = gson.toJson(books);
		return json;
	}

}
