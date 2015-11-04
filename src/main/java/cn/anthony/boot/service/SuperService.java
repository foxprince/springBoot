package cn.anthony.boot.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.anthony.boot.exception.EntityNotFound;

public class SuperService<T> {
    protected JpaRepository<T, Long> repository;

    public T create(T item) {
	return repository.save(item);
    }

    public T findById(long id) {
	return repository.findOne(id);
    }

    @Transactional(rollbackOn = EntityNotFound.class)
    public T delete(long id) throws EntityNotFound {
	T deletedT = repository.findOne(id);
	if (deletedT == null)
	    throw new EntityNotFound(getClassName().toString());
	repository.delete(deletedT);
	return deletedT;
    }

    public List<T> findAll() {
	return repository.findAll();
    }

    public Page<T> findAll(int page, int size) {
	return repository.findAll(new PageRequest(page - 1, size, Sort.Direction.DESC, "id"));
    }

    public Type getClassName() {
	return (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }
}
