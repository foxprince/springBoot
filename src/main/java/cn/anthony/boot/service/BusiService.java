package cn.anthony.boot.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.Busi;
import cn.anthony.boot.repository.BusiRepository;

@Service
@Transactional
public class BusiService extends GenericService<Busi> {
    @Resource
    protected BusiRepository repository;

    public BusiRepository getRepository() {
	return repository;
    }

    public Map<Long, String> getBusiMap() {
	Map<Long, String> m = new HashMap<Long, String>();
	for (Busi item : findAll())
	    m.put(item.getId(), item.getName());
	return m;
    }

}
