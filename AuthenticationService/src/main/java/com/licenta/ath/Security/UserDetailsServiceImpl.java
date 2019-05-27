package com.licenta.ath.Security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.licenta.ath.Feign.UserProxy;
import com.licenta.usm.Entities.AuthUser;
import com.licenta.usm.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    @Autowired
    private UserProxy userProxy;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        AuthUser authUser;
        try {
            authUser = userProxy.getUser(username).getBody();
            var appUser = new AppUser(authUser.getNickName(), authUser.getBcryptedPassword(), "USER");
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_" + "USER"); /*ADMIN*/

            return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
    }

    private static class AppUser {
        private String username, password;
        private String role;

        public AppUser(final String username, final String password, final String role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(final String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(final String password) {
            this.password = password;
        }
        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}