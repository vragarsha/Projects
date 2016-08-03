package movies.rest.repository;

import java.util.List;

import movies.rest.entity.Movie;

public interface MovieRepository {

	public List<Movie> findAll();

	public Movie findOne(String id);

	public Movie findByTitle(String title);
	public List<Movie> findByYear();
	public List<Movie> findByGenre();
	public List<Movie> findByIMDBRatings();
	public List<Movie> findByIMDBVotes();

	public Movie create(Movie mov);

	public Movie update(Movie mov);

	public void delete(Movie mov);
}
