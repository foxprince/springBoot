package cn.anthony.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.anthony.boot.doman.ChannelCode;

public interface ChannelCodeRepository extends JpaRepository<ChannelCode, Long> {

    List<ChannelCode> findByChannelId(Long channelId);

}
