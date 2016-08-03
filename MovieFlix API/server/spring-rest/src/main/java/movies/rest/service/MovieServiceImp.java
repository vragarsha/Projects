package movies.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movies.rest.entity.Movie;
import movies.rest.exception.MovieAlreadyExistsException;
import movies.rest.exception.MovieNotFoundException;
import movies.rest.repository.MovieRepository;

@Service
public class MovieServiceImp implements MovieService {

	@Autowired
	MovieRepository repository;

	@Override
	public List<Movie> findAll() {
		return repository.findAll();
	}

	@Override
	public Movie findOne(String id) {
		Movie existing = repository.findOne(id);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with id:" + id + " not found");
		}
		return existing;
	}

	@Override
	@Transactional
	public Movie create(Movie mov) {
		Movie existing = repository.findByTitle(mov.getTitle());
		if (existing != null) {
			throw new MovieAlreadyExistsException("Title is already in use: " + mov.getTitle());
		}
		return repository.create(mov);
	}

	@Override
	@Transactional
	public Movie update(String id, Movie mov) {
		Movie existing = repository.findOne(id);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with id:" + id + " not found");
		}
		return repository.update(mov);
	}
	@Override
	@Transactional
	public void delete(String id) {
		Movie existing = repository.findOne(id);
		if (existing == null) {
			throw new MovieNotFoundException("Movie with id:" + id + " not found");
		}
		repository.delete(existing);
	}
}
