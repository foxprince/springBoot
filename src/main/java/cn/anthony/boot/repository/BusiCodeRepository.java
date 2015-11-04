package cn.anthony.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.anthony.boot.doman.BusiCode;

public interface BusiCodeRepository extends JpaRepository<BusiCode, Long> {

    List<BusiCode> findByBusiId(long busiId);

}
