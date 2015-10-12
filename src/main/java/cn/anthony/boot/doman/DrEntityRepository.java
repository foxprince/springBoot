package cn.anthony.boot.doman;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DrEntityRepository extends JpaRepository<DrEntity, Long> {
//,JpaSpecificationExecutor<DrEntity> {

	@Query("select distinct spId from DrEntity p order by p.spId")
	List<String> getSpList();

	@Query("select distinct channelId from DrEntity p order by p.channelId")
	List<String> getChannelList();
	
	//public Page<DrEntity> findAll(Predicate predicate, Pageable p);

	public Page<DrEntity> findAll(Specification<DrEntity> spec, Pageable p);

	DrEntity findBySpIdAndLinkId(String spId, String linkId);


}
