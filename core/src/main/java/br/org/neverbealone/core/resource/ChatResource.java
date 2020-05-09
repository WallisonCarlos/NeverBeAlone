package br.org.neverbealone.core.resource;

import static java.lang.String.format;

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
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import br.org.neverbealone.core.exception.NotFoundEntityException;
import br.org.neverbealone.core.model.Chat;
import br.org.neverbealone.core.model.Message;
import br.org.neverbealone.core.model.User;
import br.org.neverbealone.core.service.ChatService;

@RestController
@RequestMapping({"/chats"})
public class ChatResource implements Resource<Chat>{

	@Autowired
	private ChatService chatService;
	@Autowired
	private SimpMessageSendingOperations simpMessageSendingOperations;
	
	@Override
	@GetMapping
	public List<Chat> index() {
		return chatService.findAllNotDeleted();
	}

	@Override
	@GetMapping("/{id}")
	public Chat show(@PathVariable("id") String id) throws NotFoundEntityException {
		return chatService.findOneNotDeletedBy(id);
	}

	@Override
	@PostMapping
	public Chat create(@RequestBody @Valid Chat entity) {
		return chatService.create(entity);
	}

	@Override
	@PutMapping("/{id}")
	public Chat update(@RequestBody @Valid Chat entity, @PathVariable("id") String id) throws NotFoundEntityException {
		return chatService.update(entity, id);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		return chatService.delete(id);
	}
	
	@MessageMapping("/{chat}/send")
	public void sendMessage(@DestinationVariable("chat") String chat, @Payload Message message) {
		simpMessageSendingOperations.convertAndSend(format("/channel/%s", chat), message);
	}
	
	@MessageMapping("/{chat}/add-user")
	public void addUser(@DestinationVariable("chat") String chat, @Payload User user, SimpMessageHeaderAccessor simpMessageHeaderAccessor) {
		simpMessageHeaderAccessor.getSessionAttributes().put("user", user);
	}
	
}
