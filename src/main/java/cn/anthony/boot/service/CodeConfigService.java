package cn.anthony.boot.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.CodeConfig;
import cn.anthony.boot.doman.CodeConfigRepository;
import cn.anthony.boot.exception.EntityNotFound;

@Service
@Transactional
public class CodeConfigService {
	@Resource
    private CodeConfigRepository drRepository;
 
    public CodeConfig create(CodeConfig drEntity) {
        CodeConfig createdCodeConfig = drEntity;
        return drRepository.save(createdCodeConfig);
    }
     
    public CodeConfig findById(long id) {
        return drRepository.findOne(id);
    }
 
    public List<CodeConfig> findAll() {
        return drRepository.findAll();
    }
    
    @Transactional(rollbackOn=EntityNotFound.class)
    public CodeConfig update(CodeConfig item) throws EntityNotFound {
        CodeConfig updatedCodeConfig = drRepository.findOne(item.getId());
        if (updatedCodeConfig == null)
            throw new EntityNotFound(CodeConfig.class.getName());
        item.setId(updatedCodeConfig.getId());
        drRepository.delete(updatedCodeConfig);
        drRepository.saveAndFlush(item);
        return updatedCodeConfig;
    }
}
