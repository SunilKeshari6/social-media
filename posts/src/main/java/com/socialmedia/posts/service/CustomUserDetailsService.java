package com.socialmedia.posts.service;

import com.socialmedia.posts.entity.UserPrincipal;
import com.socialmedia.posts.entity.Users;
import com.socialmedia.posts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) //throws UsernameNotFoundException {
    {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException( "User not found");
        }
       return new UserPrincipal(user.getUsername(), user.getPassword(), authorities());
    }

    public Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
    }
}
