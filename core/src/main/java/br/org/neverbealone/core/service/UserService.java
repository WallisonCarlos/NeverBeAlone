package br.org.neverbealone.core.service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.org.neverbealone.core.exception.NotFoundEntityException;
import br.org.neverbealone.core.model.User;
import br.org.neverbealone.core.repository.UserRepository;

@Service
public class UserService implements br.org.neverbealone.core.service.Service<User>{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAllNotDeleted() {
		return userRepository.findAllNotDeleted();
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOneBy(Long id) throws NotFoundEntityException {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new NotFoundEntityException("User not found");
		}
	}
	
	@Override
	public User findOneNotDeletedBy(Long id) throws NotFoundEntityException {
		Optional<User> user = userRepository.findNotDeletedBy(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new NotFoundEntityException("User not found");
		}
	}
	
	public User findByEmail(String email) throws NotFoundEntityException {
		Predicate<User> predicate = u -> u.getDeleted() == null;
		Optional<User> user = userRepository.findByEmail(email).filter(predicate);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new NotFoundEntityException("User not found");
		}
	}
	
	public Boolean userExistsByEmail(String email) {
		try {
			findByEmail(email);
			return TRUE;
		} catch (NotFoundEntityException e) {
			return FALSE;
		}
	}
	
	public Boolean userExistsByUsername(String username) {
		try {
			findByUsername(username);
			return TRUE;
		} catch (NotFoundEntityException e) {
			return FALSE;
		}
	}
	
	public User findByUsername(String username) throws NotFoundEntityException {
		Predicate<User> predicate = u -> u.getDeleted() == null;
		Optional<User> user = userRepository.findByUsername(username).filter(predicate);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new NotFoundEntityException("User not found");
		}
	}

	@Override
	public User create(User entity) {
		entity.setCreated(ZonedDateTime.now());
		entity.setPassword(BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()));
		return userRepository.save(entity);
	}

	@Override
	public User update(User entity, Long id) throws NotFoundEntityException {
		Optional<User> user = userRepository.findNotDeletedBy(id);
		if (!user.isPresent()) {
			throw new NotFoundEntityException("User not found!");
		}
		entity.setUpdated(ZonedDateTime.now());
		return userRepository.save(entity);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		Predicate<User> predicate = e -> e.getDeleted() == null;
		return userRepository.findById(id).filter(predicate)
		        .map(record -> {
		        	record.setDeleted(ZonedDateTime.now());
		        	userRepository.save(record);
		            return ResponseEntity.ok().build();
		        }).orElse(ResponseEntity.notFound().build());
	}

}
