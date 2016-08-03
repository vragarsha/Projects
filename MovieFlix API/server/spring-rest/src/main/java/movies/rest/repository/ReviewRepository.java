package movies.rest.repository;

import java.util.List;

import movies.rest.entity.Review;

public interface ReviewRepository {

	public List<Review> findAll();

	public Review findOne(String id);

	
	public Review create(Review rev);

	public Review update(Review rev);

	public void delete(Review rev);
}