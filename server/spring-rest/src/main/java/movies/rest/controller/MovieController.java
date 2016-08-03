package movies.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import movies.rest.entity.Movie;
import movies.rest.service.MovieService;

@RestController
@RequestMapping(path = "movies")
public class MovieController {
	
	@Autowired
	MovieService mService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAll() {
		return mService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie findOne(@PathVariable("id") String movId) {
		return mService.findOne(movId);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie create(@RequestBody Movie mov) {
		return mService.create(mov);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie update(@PathVariable("id") String movId, @RequestBody Movie mov) {
		return mService.update(movId, mov);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String movId) {
		mService.delete(movId);
	}

}
