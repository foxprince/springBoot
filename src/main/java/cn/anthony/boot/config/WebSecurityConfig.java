package cn.anthony.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
		.antMatchers("/", "/resources/**", "/hzpz", "/zxt", "/rmwmo", "/rmwdr", "/rmwivr", "/bestpr", "/fhddo", "/channel/list.json",
			"/busi/list.json")
		.permitAll().antMatchers("/users/**").hasAuthority("ADMIN").anyRequest().fullyAuthenticated().and().formLogin().loginPage("/login")
		.failureUrl("/login?error").usernameParameter("email").permitAll().and().logout().logoutUrl("/logout").deleteCookies("remember-me")
		.logoutSuccessUrl("/").permitAll().and().rememberMe();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}

class PasswordCrypto {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static PasswordCrypto instance;

    public static PasswordCrypto getInstance() {
	if (instance == null) {
	    instance = new PasswordCrypto();
	}
	return instance;
    }

    public String encrypt(String str) {
	return passwordEncoder.encode(str);
    }
}