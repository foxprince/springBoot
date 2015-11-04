package cn.anthony.boot.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.Busi;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.boot.repository.BusiRepository;

@Service
@Transactional
public class BusiService extends SuperService<Busi> {
    @Resource
    protected BusiRepository repository;

    public BusiService() {
    }

    public Busi create(Busi item) {
	return repository.save(item);
    }

    public Busi findById(long id) {
	return repository.findOne(id);
    }

    @Transactional(rollbackOn = EntityNotFound.class)
    public Busi delete(long id) throws EntityNotFound {
	Busi deletedT = repository.findOne(id);
	if (deletedT == null)
	    throw new EntityNotFound(getClassName().toString());
	repository.delete(deletedT);
	return deletedT;
    }

    public List<Busi> findAll() {
	return repository.findAll();
    }

    public Busi update(Busi item) throws EntityNotFound {
	return repository.save(item);
    }

    public Map<Boolean, String> getActiveMap() {
	Map<Boolean, String> m = new LinkedHashMap<Boolean, String>();
	m.put(Boolean.TRUE, "开");
	m.put(Boolean.FALSE, "关");
	return m;
    }

    public Map<Long, String> getBusiMap() {
	Map<Long, String> m = new HashMap<Long, String>();
	for (Busi item : findAll())
	    m.put(item.getId(), item.getName());
	return m;
    }

    private Map<String, String> getSelectMap() {
	Map<String, String> m = new HashMap<String, String>();
	for (Busi item : findAll())
	    m.put("/busiCode/?busiId=" + item.getId(), item.getName());
	return m;
    }

}
