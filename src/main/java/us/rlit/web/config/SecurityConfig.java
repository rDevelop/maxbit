package us.rlit.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import us.rlit.web.users.MaxUser;
import us.rlit.web.users.MaxUsers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rob on 11/19/16.
 */
@EnableWebSecurity
@ComponentScan(value = "us.rlit")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MaxAuthenticationProvider authProvider;

    @Bean
    public PasswordEncoder keyEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/index", "/views/**").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/trading/**").access("hasRole('USER') or hasRole('ADMIN')")
                .and()
                .formLogin().loginPage("/index/login")
                .successForwardUrl("/trading")
                .failureUrl("/login-error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/views/index");

    }
    // @formatter:on

    // @formatter:off
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
    // @formatter:on

    /**
     * Created by rob on 12/20/16.
     */
    @Component
    public static class MaxAuthenticationProvider implements AuthenticationProvider {
        private static MaxUsers maxUsers;
        @Autowired
        public PasswordEncoder keyEncoder;

        {
            maxUsers = maxUsers();
        }

        private static MaxUsers maxUsers() {
            String userFile = "maxusers.ser";
            MaxUsers users = new MaxUsers();
            try (
                    FileInputStream fileInputStream = new FileInputStream(userFile);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
                users = (MaxUsers) objectInputStream.readObject();
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Error getting serial file. " + e.getMessage());
            }
            return users;
        }

        @Override
        public Authentication authenticate(Authentication authentication)
                throws AuthenticationException {
            String name = authentication.getName();
            String key = authentication.getCredentials().toString();
            MaxUser user = userAuthorization(authentication);
            if (user != null) {
                final List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority(user.getRole()));
                final UserDetails principal = new User(name, key, grantedAuths);
                final Authentication auth = new UsernamePasswordAuthenticationToken(principal, key, grantedAuths);
                return auth;
            } else {
                return null;
            }
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return authentication.equals(UsernamePasswordAuthenticationToken.class);

        }

        private MaxUser userAuthorization(Authentication auth) {
            MaxUser user = maxUsers.getUser(auth.getName());
            if (user == null) {
                return null;
            } else if (user.getUsername().equals(auth.getName()) &&
                    keyEncoder.matches(auth.getCredentials().toString(), user.getKey())) {
                return user;
            }
            return null;
        }
    }
}
