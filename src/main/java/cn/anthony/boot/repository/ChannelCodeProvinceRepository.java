package cn.anthony.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.anthony.boot.doman.ChannelCodeProvince;

public interface ChannelCodeProvinceRepository extends JpaRepository<ChannelCodeProvince, Long> {

    List<ChannelCodeProvince> findByChannelIdAndCodeId(Long channelId, Long codeId);

    List<ChannelCodeProvince> findByProvinceAndChannelId(String province, Long channelId);

}
