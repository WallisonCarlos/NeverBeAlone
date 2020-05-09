package br.org.neverbealone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.neverbealone.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	@Query(value = "SELECT * FROM user WHERE user.id = :id AND user.deleted = \"null\"",
			nativeQuery = true)
	public Optional<Message> findNotDeletedBy(Long id);
	@Query(value = "SELECT * FROM user WHERE user.deleted = \"null\"",
			nativeQuery = true)
	public List<Message> findAllNotDeleted();
	
}
