package cn.anthony.boot.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
        return repository.save(drEntity);
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
 
    public Page<SpecialPhone> findAll(String phone, int page,int size) {
    	if(phone!=null)
    		return new PageImpl<SpecialPhone>(repository.findByPhone(phone));
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

	public int batchAdd(String stype, Set<String> phoneSet) {
		int total = 0;
		for(String phone : phoneSet)
			if(create(new SpecialPhone(stype,phone))!=null)
				total ++;
		return total;
	}

	public SpecialPhone delete(Long id) throws EntityNotFound {
		SpecialPhone deletedBaseEntity = repository.findOne(id);
        if (deletedBaseEntity == null)
            throw new EntityNotFound(SpecialPhone.class.getName());
        repository.delete(deletedBaseEntity);
        return deletedBaseEntity;
	}
}
