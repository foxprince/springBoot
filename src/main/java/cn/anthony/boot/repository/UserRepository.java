package cn.anthony.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.anthony.boot.doman.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
