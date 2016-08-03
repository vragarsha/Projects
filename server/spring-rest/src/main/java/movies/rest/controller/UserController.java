package movies.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import movies.rest.entity.User;
import movies.rest.service.UserService;

@RestController
@RequestMapping(path = "users")
public class UserController {
	
	@Autowired
	UserService uService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<User> findAll() {
		return uService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User findOne(@PathVariable("id") String uId) {
		return uService.findOne(uId);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User create(@RequestBody User user) {
		return uService.create(user);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User update(@PathVariable("id") String uId, @RequestBody User user) {
		return uService.update(uId, user);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "{id}")
	public void delete(@PathVariable("id") String uId) {
		uService.delete(uId);
	}

}
