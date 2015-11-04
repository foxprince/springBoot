package cn.anthony.boot.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.BusiCode;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.boot.repository.BusiCodeRepository;

@Service
public class BusiCodeService extends SuperService<BusiCode> {
    @Resource
    protected BusiCodeRepository repository;

    public BusiCodeService() {
    }

    public BusiCode create(BusiCode item) {
	return repository.save(item);
    }

    public BusiCode findById(long id) {
	return repository.findOne(id);
    }

    @Transactional(rollbackOn = EntityNotFound.class)
    public BusiCode delete(long id) throws EntityNotFound {
	BusiCode deleted = repository.findOne(id);
	if (deleted == null)
	    throw new EntityNotFound(getClassName().toString());
	repository.delete(deleted);
	return deleted;
    }

    public List<BusiCode> findAll() {
	return repository.findAll();
    }

    public List<BusiCode> findByBusi(long busiId) {
	return repository.findByBusiId(busiId);
    }

    public BusiCode update(BusiCode item) throws EntityNotFound {
	return repository.save(item);
    }
}
