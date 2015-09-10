package cn.anthony.boot.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.BaseEntity;
import cn.anthony.boot.doman.BaseEntityRepository;
import cn.anthony.boot.exception.BaseEntityNotFound;

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
 
    @Transactional(rollbackOn=BaseEntityNotFound.class)
    public BaseEntity delete(long id) throws BaseEntityNotFound {
        BaseEntity deletedBaseEntity = shopRepository.findOne(id);
         
        if (deletedBaseEntity == null)
            throw new BaseEntityNotFound();
         
        shopRepository.delete(deletedBaseEntity);
        return deletedBaseEntity;
    }
 
    @Transactional
    public List<BaseEntity> findAll() {
        return shopRepository.findAll();
    }
 
    @Transactional(rollbackOn=BaseEntityNotFound.class)
    public BaseEntity update(BaseEntity shop) throws BaseEntityNotFound {
        BaseEntity updatedBaseEntity = shopRepository.findOne(shop.getId());
        if (updatedBaseEntity == null)
            throw new BaseEntityNotFound();
        updatedBaseEntity.setTitle(shop.getTitle());
        updatedBaseEntity.setDescription(shop.getDescription());
        updatedBaseEntity.setModificationTime(Calendar.getInstance().getTime());
        return updatedBaseEntity;
    }
}
