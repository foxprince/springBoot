package cn.anthony.boot.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.anthony.boot.doman.User;
import cn.anthony.boot.repository.CurrentUser;
import cn.anthony.boot.repository.UserRepository;

@Service
@Transactional
public class UserService extends GenericService<User> implements UserDetailsService {

    @Resource
    protected UserRepository repository;

    public UserRepository getRepository() {
	return repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	User user = repository.findByEmail(email);
	return new CurrentUser(user);
    }

}
