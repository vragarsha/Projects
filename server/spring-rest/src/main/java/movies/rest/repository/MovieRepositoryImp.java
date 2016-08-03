package movies.rest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import movies.rest.entity.Movie;

@Repository
public class MovieRepositoryImp implements MovieRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Movie> findAll() {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findAll", Movie.class);
		return query.getResultList();
	}

	@Override
	public Movie findOne(String id) {
		return em.find(Movie.class, id);
	}

	@Override
	public Movie findByTitle(String title) {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findByTitle", Movie.class);
		query.setParameter("pTitle", title);
		List<Movie> movies = query.getResultList();
		if (movies != null && movies.size() == 1) {
			return movies.get(0);
		}
		return null;
	}
	
	@Override
	public List<Movie> findByYear() {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findByYear", Movie.class);
		return query.getResultList();
	}
	
	@Override
	public List<Movie> findByGenre() {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findByGenre", Movie.class);
		return query.getResultList();
	}
	
	@Override
	public List<Movie> findByIMDBRatings() {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findByIMDBRatings", Movie.class);
		return query.getResultList();
	}
	
	@Override
	public List<Movie> findByIMDBVotes() {
		TypedQuery<Movie> query = em.createNamedQuery("Movie.findByIMDBVotes", Movie.class);
		return query.getResultList();
	}

	@Override
	public Movie create(Movie mov) {
		em.persist(mov);
		return null;
	}

	@Override
	public Movie update(Movie mov) {
		return em.merge(mov);
	}

	@Override
	public void delete(Movie mov) {
		em.remove(mov);
		
	}

}
