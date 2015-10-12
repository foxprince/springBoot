package cn.anthony.boot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.DrEntity;
import cn.anthony.boot.doman.DrEntityRepository;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.util.StringTools;

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
    
    public List<String> getSpList() {
    	return drRepository.getSpList();
    }
    
    public DrEntity findByLinkId(String spId,String linkId) {
    	Page<DrEntity> p = drRepository.findBySpIdAndLinkId(spId,linkId,new PageRequest(0, 1, Sort.Direction.DESC, "id"));
    	if(p!=null&&p.getSize()>0)
    		return p.getContent().get(0);
    	return null;
    }
    
    public Map<String,String> getSpMap() {
    	Map<String,String> m = new HashMap<String,String>();
    	for(String spId : getSpList())
    		m.put(spId, spId);
    	return m;
    }
    public List<String> getChannelList() {
    	return drRepository.getChannelList();
    }
    public Map<String,String> getChannelMap() {
    	Map<String,String> m = new HashMap<String,String>();
    	for(String cId : getChannelList())
    		m.put(cId, cId);
    	return m;
    }
    
    public Page<DrEntity> find(String spId, String channelId, String phoneStr, Date beginTime, Date endTime, int page,int size) {
    	PageRequest pageRequest = new PageRequest(page - 1, size, Sort.Direction.DESC, "id");
    	return drRepository.findAll(findByCriteria(spId,channelId,StringUtils.isEmpty(phoneStr)?null:StringTools.splitString(phoneStr," ,"),beginTime,endTime),pageRequest);
    }
    
    private Specification<DrEntity> findByCriteria(final String spId, final String channelId, final List<String> phones, final Date beginTime, final Date endTime) {
        return new Specification<DrEntity>() {
            @Override
            public Predicate toPredicate(Root<DrEntity> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(spId)&&!spId.equals("0")) 
                    predicates.add(cb.equal(root.get("spId"), spId));
                if (!StringUtils.isEmpty(channelId)&&!channelId.equals("0")) 
                    predicates.add(cb.equal(root.get("channelId"), channelId));
                if (phones!=null&&phones.size()>0) 
                    predicates.add((root.get("phone").in(phones)));
                if (beginTime != null) 
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("recvTime"), beginTime));
                if (endTime != null) 
                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("recvTime"), endTime));
                return cb.and(predicates.toArray(new Predicate[] {}));
            }
        };
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
