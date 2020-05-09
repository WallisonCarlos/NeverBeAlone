package br.org.neverbealone.core.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.org.neverbealone.core.exception.NotFoundEntityException;
import br.org.neverbealone.core.model.Message;
import br.org.neverbealone.core.repository.MessageRepository;

@Service
public class MessageService implements br.org.neverbealone.core.service.Service<Message>{

	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public List<Message> findAllNotDeleted() {
		return messageRepository.findAllNotDeleted();
	}
	
	@Override
	public List<Message> findAll() {
		return messageRepository.findAll();
	}

	@Override
	public Message findOneBy(String id) throws NotFoundEntityException {
		Optional<Message> entity = messageRepository.findById(id);
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new NotFoundEntityException("Message not found");
		}
	}
	
	@Override
	public Message findOneNotDeletedBy(String id) throws NotFoundEntityException {
		Optional<Message> entity = messageRepository.findNotDeletedBy(id);
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new NotFoundEntityException("Message not found");
		}
	}
	
	@Override
	public Message create(Message entity) {
		entity.setCreated(ZonedDateTime.now());
		return messageRepository.save(entity);
	}

	@Override
	public Message update(Message entity, String id) throws NotFoundEntityException {
		Optional<Message> message = messageRepository.findNotDeletedBy(id);
		if (!message.isPresent()) {
			throw new NotFoundEntityException("Message not found!");
		}
		entity.setUpdated(ZonedDateTime.now());
		return messageRepository.save(entity);
	}

	@Override
	public ResponseEntity<?> delete(String id) {
		Predicate<Message> predicate = e -> e.getDeleted() == null;
		return messageRepository.findById(id).filter(predicate)
		        .map(record -> {
		        	record.setDeleted(ZonedDateTime.now());
		        	messageRepository.save(record);
		            return ResponseEntity.ok().build();
		        }).orElse(ResponseEntity.notFound().build());
	}
	
}
