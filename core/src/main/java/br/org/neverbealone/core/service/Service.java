package br.org.neverbealone.core.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.org.neverbealone.core.exception.NotFoundEntityException;

public interface Service<E> {

	public List<E> findAll();
	public List<E> findAllNotDeleted();
	public E findOneNotDeletedBy(String id) throws NotFoundEntityException ;
	public E findOneBy(String id) throws NotFoundEntityException ;
	public E create(E entity);
	public E update(E entity, String id) throws NotFoundEntityException ;
	public ResponseEntity<?> delete(String id);
	
}
