package br.org.neverbealone.core.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.neverbealone.core.exception.NotFoundEntityException;
import br.org.neverbealone.core.model.User;
import br.org.neverbealone.core.service.UserService;

@RestController
@RequestMapping({"/users"})
public class UserResource implements Resource<User>{

	@Autowired
	private UserService userService;
	
	@Override
	@GetMapping
	public List<User> index() {
		return userService.findAllNotDeleted();
	}

	@Override
	@GetMapping("/{id}")
	public User show(@PathVariable("id") Long id) throws NotFoundEntityException {
		return userService.findOneNotDeletedBy(id);
	}

	@Override
	@PostMapping
	public User create(@RequestBody @Valid User entity) {
		return userService.create(entity);
	}

	@Override
	@PutMapping("/{id}")
	public User update(@RequestBody @Valid User entity, @PathVariable("id") Long id) throws NotFoundEntityException {
		return userService.update(entity, id);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return userService.delete(id);
	}

}
