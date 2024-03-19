package com.example.UserService.config;

import com.example.UserService.domain.UserEntity;
import com.example.UserService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> findOne = userRepository.findByEmail(email);
        UserEntity userEntity = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다"));

        return User.builder()
                .username(userEntity.getName())
                .password(userEntity.getEncryptedPwd())
                .authorities(new SimpleGrantedAuthority("ADMIN"))
                .build();
    }

    public Long findUserIdByEmail(String email) {
        Optional<UserEntity> findOne = userRepository.findByEmail(email);
        UserEntity userEntity = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다"));

        return userEntity.getId();
    }

}
