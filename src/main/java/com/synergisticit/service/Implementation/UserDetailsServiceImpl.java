package com.synergisticit.service.Implementation;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);

        List<Role> roles = user.getUserRoles();
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for(Role role : roles){
            System.out.println(role);
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        System.out.println(passwordEncoder.encode("admin"));


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getUserPassword(), authorities);
    }
}
