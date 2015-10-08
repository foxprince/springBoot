package cn.anthony.boot.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.DrEntity;
import cn.anthony.boot.doman.DrEntityRepository;
import cn.anthony.boot.exception.EntityNotFound;

@Service
@Transactional
public class DrService {
	@Resource
    private DrEntityRepository drRepository;
 
    public DrEntity create(DrEntity drEntity) {
        DrEntity createdDrEntity = drEntity;
        return drRepository.save(createdDrEntity);
    }
     
    public DrEntity findById(long id) {
        return drRepository.findOne(id);
    }
 
    public List<DrEntity> findAll() {
        return drRepository.findAll();
    }
 
    public Page<DrEntity> find(int page,int size) {
    	PageRequest pageRequest = new PageRequest(page - 1, size, Sort.Direction.DESC, "id");
    	return drRepository.findAll(pageRequest);
    }
    
    @Transactional(rollbackOn=EntityNotFound.class)
    public DrEntity update(DrEntity item) throws EntityNotFound {
        DrEntity updatedDrEntity = drRepository.findOne(item.getId());
        if (updatedDrEntity == null)
            throw new EntityNotFound(DrEntity.class.getName());
        updatedDrEntity.setChannelId(item.getChannelId());
        updatedDrEntity.setForwardStatus(item.getForwardStatus());
        updatedDrEntity.setForwardTime(item.getForwardTime());
        updatedDrEntity.setDeductFlag(item.getDeductFlag());
        return updatedDrEntity;
    }
}
