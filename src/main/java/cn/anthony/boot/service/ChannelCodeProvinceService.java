package cn.anthony.boot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.ChannelCodeProvince;
import cn.anthony.boot.repository.ChannelCodeProvinceRepository;

@Service
@Transactional
public class ChannelCodeProvinceService extends GenericService<ChannelCodeProvince> {
    @Resource
    protected ChannelCodeProvinceRepository repository;

    @Override
    public JpaRepository<ChannelCodeProvince, Long> getRepository() {
	return repository;
    }

    public void addBatch(Long channelId, Long codeId, Collection<String> provinces) {
	repository.delete(repository.findByChannelIdAndCodeId(channelId, codeId));
	List<ChannelCodeProvince> l = new ArrayList<ChannelCodeProvince>();
	for (String province : provinces)
	    l.add(new ChannelCodeProvince(channelId, codeId, province));
	repository.save(l);
    }

    public List<ChannelCodeProvince> findByChannelIdAndCodeId(Long channelId, Long codeId) {
	return repository.findByChannelIdAndCodeId(channelId, codeId);
    }

    public List<String> findCheckProvinceList(Long channelId, Long codeId) {
	List<String> l = new ArrayList<String>();
	for (ChannelCodeProvince cc : findByChannelIdAndCodeId(channelId, codeId))
	    l.add(cc.getProvince());
	return l;
    }

    public List<ChannelCodeProvince> findByProvinceAndChannelId(String province, Long channelId) {
	return repository.findByProvinceAndChannelId(province, channelId);
    }
}
