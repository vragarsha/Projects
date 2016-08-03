package movies.rest.service;

import java.util.List;

import movies.rest.entity.Movie;

public interface MovieService {

	public List<Movie> findAll();

	public Movie findOne(String id);
	
	public Movie create(Movie mov);
	public Movie update(String id, Movie mov);
	public void delete(String id);

}
