package cn.anthony.boot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.BusiCode;
import cn.anthony.boot.doman.ChannelCode;
import cn.anthony.boot.repository.ChannelCodeRepository;

@Service
@Transactional
public class ChannelCodeService extends GenericService<ChannelCode> {
    @Resource
    protected ChannelCodeRepository repository;
    @Resource
    BusiCodeService codeService;

    @Override
    public JpaRepository<ChannelCode, Long> getRepository() {
	return repository;
    }

    public void addBatch(Long channelId, Collection<Long> codeIds) {
	repository.delete(repository.findByChannelId(channelId));
	List<ChannelCode> l = new ArrayList<ChannelCode>();
	for (Long codeId : codeIds)
	    l.add(new ChannelCode(channelId, codeId));
	repository.save(l);
    }

    public List<ChannelCode> findByChannelId(Long channelId) {
	return repository.findByChannelId(channelId);
    }

    public List<Long> findCheckCodeList(Long channelId) {
	List<Long> l = new ArrayList<Long>();
	for (ChannelCode cc : findByChannelId(channelId))
	    l.add(cc.getCodeId());
	return l;
    }

    public List<BusiCode> findCheckCodes(Long channelId) {
	List<BusiCode> l = new ArrayList<BusiCode>();
	for (ChannelCode cc : findByChannelId(channelId))
	    l.add(codeService.findById(cc.getCodeId()));
	return l;
    }

    public Map<Long, String> findCheckedCodeMap(Long channelId) {
	Map<Long, String> m = new TreeMap<Long, String>();
	for (ChannelCode cc : findByChannelId(channelId)) {
	    BusiCode bc = codeService.findById(cc.getCodeId());
	    m.put(bc.getId(), bc.getCode());
	}
	return m;
    }
}
