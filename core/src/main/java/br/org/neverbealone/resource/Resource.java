package br.org.neverbealone.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.org.neverbealone.exception.NotFoundEntityException;

public interface Resource<E> {
	public List<E> index();
	public E show(Long id) throws NotFoundEntityException ;
	public E create(E entity);
	public E update(E entity, Long id) throws NotFoundEntityException ;
	public ResponseEntity<?> delete(Long id);
}
