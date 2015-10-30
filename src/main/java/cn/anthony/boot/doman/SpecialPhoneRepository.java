package cn.anthony.boot.doman;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

public interface SpecialPhoneRepository extends JpaRepository<SpecialPhone, Long> {

	@Async
	Future<SpecialPhone> findById(Long id);
	
	List<SpecialPhone> findByPhone(String phone);

}
