package com.example.UserService.repository;

import com.example.UserService.domain.UserEntity;
import com.example.UserService.dto.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<UserEntity> findById(Long userId);

    default UserResponse findUserResponseByUserId(Long userId) {
        Optional<UserEntity> userEntityOptional = findById(userId);
        UserEntity userEntity = userEntityOptional.get();
        return new UserResponse(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getPhoneNumber(),
                userEntity.isApproved());
        }

    default UserResponse findUserResponseByEmail(String email) {
        Optional<UserEntity> userEntityOptional = findByEmail(email);
        UserEntity userEntity = userEntityOptional.get();
        return new UserResponse(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getPhoneNumber(),
                userEntity.isApproved());
    }
}
