package cn.anthony.boot.repository;

import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import cn.anthony.boot.doman.CodeConfig;

public interface CodeConfigRepository extends JpaRepository<CodeConfig, Long> {

    @Async
    Future<CodeConfig> findById(Long id);

}
