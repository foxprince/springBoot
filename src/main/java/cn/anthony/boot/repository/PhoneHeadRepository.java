package cn.anthony.boot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.anthony.boot.doman.PhoneHead;

public interface PhoneHeadRepository extends JpaRepository<PhoneHead, Long> {

    Page<PhoneHead> findByProvince(String province, Pageable request);

    Page<PhoneHead> findByProvinceAndCity(String province, String city, Pageable request);

    @Query("select distinct province from PhoneHead p order by p.province")
    List<String> findAllProvince();

    @Query("select distinct city from PhoneHead p where p.province= ?1")
    List<String> findAllCities(String province);

    List<PhoneHead> findByHead(String head);

}
