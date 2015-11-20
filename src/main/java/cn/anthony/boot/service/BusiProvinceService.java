package cn.anthony.boot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.BusiProvince;
import cn.anthony.boot.repository.BusiProvinceRepository;

@Service
@Transactional
public class BusiProvinceService extends GenericService<BusiProvince> {
    @Resource
    protected BusiProvinceRepository repository;

    @Override
    public JpaRepository<BusiProvince, Long> getRepository() {
	return repository;
    }

    public void addBatch(Long busiId, Collection<String> provinces) {
	repository.delete(repository.findByBusiId(busiId));
	List<BusiProvince> l = new ArrayList<BusiProvince>();
	for (String province : provinces)
	    l.add(new BusiProvince(busiId, province));
	repository.save(l);
    }

    public List<BusiProvince> findByBusiId(Long busiId) {
	return repository.findByBusiId(busiId);
    }

    public List<String> findCheckProvinceList(Long busiId) {
	List<String> l = new ArrayList<String>();
	for (BusiProvince cc : findByBusiId(busiId))
	    l.add(cc.getProvince());
	return l;
    }
}
