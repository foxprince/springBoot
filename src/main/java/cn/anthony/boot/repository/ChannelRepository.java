package cn.anthony.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.anthony.boot.doman.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

}
