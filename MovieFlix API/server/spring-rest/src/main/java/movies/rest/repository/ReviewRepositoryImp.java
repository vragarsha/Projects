package movies.rest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import movies.rest.entity.Review;

@Repository
public class ReviewRepositoryImp implements ReviewRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Review> findAll() {
		TypedQuery<Review> query = em.createNamedQuery("Review.findAll", Review.class);
		return query.getResultList();
	}

	@Override
	public Review findOne(String id) {
		return em.find(Review.class, id);
	}



	@Override
	public Review create(Review rev) {
		em.persist(rev);
		return null;
	}

	@Override
	public Review update(Review rev) {
		return em.merge(rev);
	}

	@Override
	public void delete(Review rev) {
		em.remove(rev);
		
	}

}
