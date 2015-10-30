package cn.anthony.boot.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.SpecialPhone;
import cn.anthony.boot.doman.SpecialPhoneRepository;
import cn.anthony.boot.exception.EntityNotFound;

@Service
@Transactional
public class SpecialPhoneService {
	@Resource
    private SpecialPhoneRepository repository;
 
    public SpecialPhone create(SpecialPhone drEntity) {
        SpecialPhone createdSpecialPhone = drEntity;
        return repository.save(createdSpecialPhone);
    }
     
    public SpecialPhone findById(long id) {
        return repository.findOne(id);
    }
    public boolean isSpecial(String phone) {
    	return findByPhone(phone)!=null?true:false;
    }
    public SpecialPhone findByPhone(String phone) {
        List<SpecialPhone> l = repository.findByPhone(phone);
        if(l!=null&&l.size()>0)
        	return l.get(0);
    	return null;
    }
 
    public Page<SpecialPhone> findAll(int page,int size) {
    	return repository.findAll(new PageRequest(page - 1, size, Sort.Direction.DESC, "id"));
    }
    
    @Transactional(rollbackOn=EntityNotFound.class)
    public SpecialPhone update(SpecialPhone item) throws EntityNotFound {
        SpecialPhone updatedSpecialPhone = repository.findOne(item.getId());
        if (updatedSpecialPhone == null)
            throw new EntityNotFound(SpecialPhone.class.getName());
        item.setId(updatedSpecialPhone.getId());
        repository.delete(updatedSpecialPhone);
        repository.saveAndFlush(item);
        return updatedSpecialPhone;
    }
}
