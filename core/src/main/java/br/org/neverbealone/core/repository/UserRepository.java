package br.org.neverbealone.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.neverbealone.core.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	@Query(value = "SELECT * FROM user WHERE user.id = :id AND user.deleted = \"null\"",
			nativeQuery = true)
	public Optional<User> findNotDeletedBy(String id);
	@Query(value = "SELECT * FROM user WHERE user.deleted = \"null\"",
			nativeQuery = true)
	public List<User> findAllNotDeleted();
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    
}
