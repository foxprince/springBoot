package cn.anthony.boot.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.BaseEntity;
import cn.anthony.boot.doman.BaseEntityRepository;
import cn.anthony.boot.exception.EntityNotFound;

@Service
public class BaseEntityService {
	@Resource
    private BaseEntityRepository shopRepository;
 
    @Transactional
    public BaseEntity create(BaseEntity shop) {
        BaseEntity createdBaseEntity = shop;
        return shopRepository.save(createdBaseEntity);
    }
     
    @Transactional
    public BaseEntity findById(long id) {
        return shopRepository.findOne(id);
    }
 
    @Transactional(rollbackOn=EntityNotFound.class)
    public BaseEntity delete(long id) throws EntityNotFound {
        BaseEntity deletedBaseEntity = shopRepository.findOne(id);
        if (deletedBaseEntity == null)
            throw new EntityNotFound(BaseEntity.class.getName());
        shopRepository.delete(deletedBaseEntity);
        return deletedBaseEntity;
    }
 
    @Transactional
    public List<BaseEntity> findAll() {
        return shopRepository.findAll();
    }
 
    @Transactional(rollbackOn=EntityNotFound.class)
    public BaseEntity update(BaseEntity shop) throws EntityNotFound {
        BaseEntity updatedBaseEntity = shopRepository.findOne(shop.getId());
        if (updatedBaseEntity == null)
            throw new EntityNotFound(BaseEntity.class.getName());
        updatedBaseEntity.setTitle(shop.getTitle());
        updatedBaseEntity.setDescription(shop.getDescription());
        updatedBaseEntity.setModificationTime(Calendar.getInstance().getTime());
        return updatedBaseEntity;
    }

	public Page<BaseEntity> find(Integer pageNumber, Integer size) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, size, Sort.Direction.DESC, "id");
	    return shopRepository.findAll(pageRequest);
	}
}
