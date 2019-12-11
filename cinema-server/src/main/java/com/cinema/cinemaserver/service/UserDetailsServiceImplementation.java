//package com.cinema.cinemaserver.service;
//
//import com.cinema.cinemaserver.domain.User;
//import com.cinema.cinemaserver.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
////Gives information about a user
//public class UserDetailsServiceImplementation implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user=userRepository.findById(username).get();
//
//        if(user==null) throw new UsernameNotFoundException(username);
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//
//        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getID()));
//
//        return new org.springframework.security.core.userdetails.User(user.getID(), user.getPassword(),
//                grantedAuthorities);
//    }
//
//
//}
