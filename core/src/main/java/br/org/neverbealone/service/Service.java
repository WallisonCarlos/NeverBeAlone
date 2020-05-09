package br.org.neverbealone.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.org.neverbealone.exception.NotFoundEntityException;

public interface Service<E> {

	public List<E> findAll();
	public List<E> findAllNotDeleted();
	public E findOneNotDeletedBy(Long id) throws NotFoundEntityException ;
	public E findOneBy(Long id) throws NotFoundEntityException ;
	public E create(E entity);
	public E update(E entity, Long id) throws NotFoundEntityException ;
	public ResponseEntity<?> delete(Long id);
	
}
