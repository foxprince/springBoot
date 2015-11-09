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

public abstract class GenericService<T> {
    public abstract JpaRepository<T, Long> getRepository();

    public T create(T item) {
	return getRepository().save(item);
    }

    public T update(T item) throws EntityNotFound {
	return getRepository().save(item);
    }

    public T findById(long id) {
	return getRepository().findOne(id);
    }

    @Transactional(rollbackOn = EntityNotFound.class)
    public T delete(long id) throws EntityNotFound {
	T deletedT = getRepository().findOne(id);
	if (deletedT == null)
	    throw new EntityNotFound(getClassName().toString());
	getRepository().delete(deletedT);
	return deletedT;
    }

    public List<T> findAll() {
	return getRepository().findAll();
    }

    public Page<T> findAll(int page, int size) {
	return getRepository().findAll(new PageRequest(page - 1, size, Sort.Direction.DESC, "id"));
    }

    public Type getClassName() {
	return (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }
}
