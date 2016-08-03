package movies.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movies.rest.entity.Review;
import movies.rest.exception.ReviewNotFoundException;
import movies.rest.repository.ReviewRepository;

@Service
public class ReviewServiceImp implements ReviewService {

	@Autowired
	ReviewRepository repository;

	@Override
	public List<Review> findAll() {
		return repository.findAll();
	}

	@Override
	public Review findOne(String id) {
		Review existing = repository.findOne(id);
		if (existing == null) {
			throw new ReviewNotFoundException("Review with id:" + id + " not found");
		}
		return existing;
	}

	@Override
	@Transactional
	public Review create(Review rev) {
		return repository.create(rev);
	}

	@Override
	@Transactional
	public Review update(String id, Review rev) {
		Review existing = repository.findOne(id);
		if (existing == null) {
			throw new ReviewNotFoundException("Review with id:" + id + " not found");
		}
		return repository.update(rev);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Review existing = repository.findOne(id);
		if (existing == null) {
			throw new ReviewNotFoundException("Review with id:" + id + " not found");
		}
		repository.delete(existing);
	}
}
