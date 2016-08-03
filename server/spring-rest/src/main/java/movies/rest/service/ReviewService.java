package movies.rest.service;

import java.util.List;

import movies.rest.entity.Review;


public interface ReviewService {

	public List<Review> findAll();

	public Review findOne(String rId);
	
	public Review create(Review rev);
	public Review update(String rId, Review rev);
	public void delete(String rId);

}