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
import br.org.neverbealone.core.model.Chat;
import br.org.neverbealone.core.service.ChatService;

@RestController
@RequestMapping({"/chats"})
public class ChatResource implements Resource<Chat>{

	@Autowired
	private ChatService chatService;
	
	@Override
	@GetMapping
	public List<Chat> index() {
		return chatService.findAllNotDeleted();
	}

	@Override
	@GetMapping("/{id}")
	public Chat show(@PathVariable("id") Long id) throws NotFoundEntityException {
		return chatService.findOneNotDeletedBy(id);
	}

	@Override
	@PostMapping
	public Chat create(@RequestBody @Valid Chat entity) {
		return chatService.create(entity);
	}

	@Override
	@PutMapping("/{id}")
	public Chat update(@RequestBody @Valid Chat entity, @PathVariable("id") Long id) throws NotFoundEntityException {
		return chatService.update(entity, id);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return chatService.delete(id);
	}
	
}
