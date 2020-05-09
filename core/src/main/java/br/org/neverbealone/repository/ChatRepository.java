package br.org.neverbealone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.neverbealone.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{

	@Query(value = "SELECT * FROM user WHERE user.id = :id AND user.deleted = \"null\"",
			nativeQuery = true)
	public Optional<Chat> findNotDeletedBy(Long id);
	@Query(value = "SELECT * FROM user WHERE user.deleted = \"null\"",
			nativeQuery = true)
	public List<Chat> findAllNotDeleted();
	
}
