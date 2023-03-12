package org.tutorial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

	public List<Book> findByQuery(String query) {
		List<Book> result = new ArrayList<>();
		Connection connection = DBManager.getInstance().getConnection();

		Statement statement;
		ResultSet rs;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			while (rs.next()) {
				String title = rs.getString("title");
				int id = rs.getInt("id");
				String author = rs.getString("author");
				Book book = new Book(id, title, author);
				result.add(book);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<Book> findByTitle(String searchText) {
		return findByQuery("select id, title, author from books where title like '" + searchText + "%'");
	}

	@Override
	public List<Book> findByAll() {
		return findByQuery("select id, title, author from books");
	}
}
