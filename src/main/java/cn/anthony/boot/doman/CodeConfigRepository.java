package cn.anthony.boot.doman;

import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

public interface CodeConfigRepository extends JpaRepository<CodeConfig, Long> {

	@Async
	Future<CodeConfig> findById(Long id);

}
