package cn.anthony.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.anthony.boot.doman.BusiProvince;

public interface BusiProvinceRepository extends JpaRepository<BusiProvince, Long> {

    List<BusiProvince> findByBusiId(Long busilId);

}
