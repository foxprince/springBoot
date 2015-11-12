package cn.anthony.boot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.anthony.boot.doman.PhoneHead;
import cn.anthony.boot.repository.PhoneHeadRepository;

@Service
public class PhoneHeadService {

    @Autowired
    private PhoneHeadRepository dao;

    private Map<String, PhoneHead> headMap;

    public static void main(String[] args) {
    }

    @Transactional
    public PhoneHead addPhoneHead(PhoneHead ph) {
	return dao.save(ph);
    }

    public Page<PhoneHead> findByProvince(String province, int pageNumber, int pageSize) {
	PageRequest request = new PageRequest(pageNumber - 1, pageSize);
	return dao.findByProvince(province, request);
    }

    public Page<PhoneHead> find(String province, String city, String phone, int pageNumber, int pageSize) {
	Pageable pageable = new PageRequest(pageNumber - 1, pageSize);
	if (phone != null && phone.length() >= 7)
	    return findByHead(phone.substring(0, 7));
	if (province == null && city == null)
	    return dao.findAll(pageable);
	if (city == null)
	    return findByProvince(province, pageNumber, pageSize);
	else
	    return dao.findByProvinceAndCity(province, city, pageable);
    }

    public PhoneHead findByHeadFromMem(String head) {
	// if(headMap==null)
	// initHeadMap();
	// return headMap.get(head);
	return findByHeadFromDB(head);
    }

    private void initHeadMap() {
	headMap = new HashMap<String, PhoneHead>();
	for (PhoneHead ph : dao.findAll())
	    headMap.put(ph.getHead(), ph);
    }

    public PhoneHead findByHeadFromDB(String head) {
	List<PhoneHead> l = dao.findByHead(head);
	if (l != null && l.size() > 0)
	    return l.get(0);
	else
	    return null;
    }

    private Page<PhoneHead> findByHead(String head) {
	return new PageImpl<PhoneHead>(dao.findByHead(head));
    }

    public List<String> getAllProvince() {
	return dao.findAllProvince();
    }

    public String getProvinceString(List<String> list) {
	StringBuffer sb = new StringBuffer();
	for (String p : list)
	    sb.append("\"" + p + "\",");
	return sb.toString().substring(0, sb.length() - 1);
    }

    public List<String> getAllCities(String province) {
	return dao.findAllCities(province);
    }

    public String getCitiesString(List<String> list) {
	StringBuffer sb = new StringBuffer();
	for (String p : list)
	    sb.append("\"" + p + "\",");
	return sb.toString().substring(0, sb.length() - 1);
    }
}
