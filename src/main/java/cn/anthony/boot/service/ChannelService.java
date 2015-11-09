package cn.anthony.boot.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.Channel;
import cn.anthony.boot.repository.ChannelRepository;

@Service
@Transactional
public class ChannelService extends GenericService<Channel> {
    @Resource
    protected ChannelRepository repository;

    public Map<Long, String> getChannelMap() {
	Map<Long, String> m = new HashMap<Long, String>();
	for (Channel item : findAll())
	    m.put(item.getId(), item.getName());
	return m;
    }

    @Override
    public JpaRepository<Channel, Long> getRepository() {
	return repository;
    }

}
