package br.org.neverbealone.core.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.org.neverbealone.core.exception.NotFoundEntityException;
import br.org.neverbealone.core.model.Chat;
import br.org.neverbealone.core.repository.ChatRepository;

@Service
public class ChatService implements br.org.neverbealone.core.service.Service<Chat>{

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public List<Chat> findAllNotDeleted() {
		return chatRepository.findAllNotDeleted();
	}
	
	@Override
	public List<Chat> findAll() {
		return chatRepository.findAll();
	}

	@Override
	public Chat findOneBy(String id) throws NotFoundEntityException {
		Optional<Chat> entity = chatRepository.findById(id);
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new NotFoundEntityException("Chat not found");
		}
	}
	
	@Override
	public Chat findOneNotDeletedBy(String id) throws NotFoundEntityException {
		Optional<Chat> entity = chatRepository.findNotDeletedBy(id);
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new NotFoundEntityException("Chat not found");
		}
	}
	
	@Override
	public Chat create(Chat entity) {
		entity.setCreated(ZonedDateTime.now());
		return chatRepository.save(entity);
	}

	@Override
	public Chat update(Chat entity, String id) throws NotFoundEntityException {
		Optional<Chat> chat = chatRepository.findNotDeletedBy(id);
		if (!chat.isPresent()) {
			throw new NotFoundEntityException("Chat not found!");
		}
		entity.setUpdated(ZonedDateTime.now());
		return chatRepository.save(entity);
	}

	@Override
	public ResponseEntity<?> delete(String id) {
		Predicate<Chat> predicate = e -> e.getDeleted() == null;
		return chatRepository.findById(id).filter(predicate)
		        .map(record -> {
		        	record.setDeleted(ZonedDateTime.now());
		        	chatRepository.save(record);
		            return ResponseEntity.ok().build();
		        }).orElse(ResponseEntity.notFound().build());
	}
	
}
