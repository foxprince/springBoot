package cn.anthony.boot.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.BusiCode;
import cn.anthony.boot.repository.BusiCodeRepository;

@Service
public class BusiCodeService extends GenericService<BusiCode> {
    @Resource
    protected BusiCodeRepository repository;

    public JpaRepository<BusiCode, Long> getRepository() {
	return this.repository;
    }

    public List<BusiCode> findByBusi(long busiId) {
	return repository.findByBusiId(busiId);
    }

}
