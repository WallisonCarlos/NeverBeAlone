package br.org.neverbealone.core.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.org.neverbealone.core.exception.NotFoundEntityException;

public interface Resource<E> {
	public List<E> index();
	public E show(String id) throws NotFoundEntityException ;
	public E create(E entity);
	public E update(E entity, String id) throws NotFoundEntityException ;
	public ResponseEntity<?> delete(String id);
}
