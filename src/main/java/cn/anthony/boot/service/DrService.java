package cn.anthony.boot.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.DrEntity;
import cn.anthony.boot.doman.DrEntityRepository;
import cn.anthony.boot.exception.EntityNotFound;

@Service
public class DrService {
	@Resource
    private DrEntityRepository drRepository;
 
    @Transactional
    public DrEntity create(DrEntity drEntity) {
        DrEntity createdDrEntity = drEntity;
        return drRepository.save(createdDrEntity);
    }
     
    @Transactional
    public DrEntity findById(long id) {
        return drRepository.findOne(id);
    }
 
    @Transactional
    public List<DrEntity> findAll() {
        return drRepository.findAll();
    }
 
    @Transactional(rollbackOn=EntityNotFound.class)
    public DrEntity update(DrEntity item) throws EntityNotFound {
        DrEntity updatedDrEntity = drRepository.findOne(item.getId());
        if (updatedDrEntity == null)
            throw new EntityNotFound(DrEntity.class.getName());
        updatedDrEntity.setChannelId(item.getChannelId());
        updatedDrEntity.setForwardStatus(item.getForwardStatus());
        updatedDrEntity.setForwardTime(item.getForwardTime());
        return updatedDrEntity;
    }
}
