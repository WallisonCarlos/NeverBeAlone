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
import br.org.neverbealone.core.model.Message;
import br.org.neverbealone.core.service.MessageService;

@RestController
@RequestMapping({"/messages"})
public class MessageResource implements Resource<Message>{

	@Autowired
	private MessageService messageService;
	
	@Override
	@GetMapping
	public List<Message> index() {
		return messageService.findAllNotDeleted();
	}

	@Override
	@GetMapping("/{id}")
	public Message show(@PathVariable("id") String id) throws NotFoundEntityException {
		return messageService.findOneNotDeletedBy(id);
	}

	@Override
	@PostMapping
	public Message create(@RequestBody @Valid Message entity) {
		return messageService.create(entity);
	}

	@Override
	@PutMapping("/{id}")
	public Message update(@RequestBody @Valid Message entity, @PathVariable("id") String id) throws NotFoundEntityException {
		return messageService.update(entity, id);
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		return messageService.delete(id);
	}
	
}
