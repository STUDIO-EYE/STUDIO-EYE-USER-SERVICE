package com.example.UserService.repository;

import com.example.UserService.domain.UserEntity;
import com.example.UserService.dto.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);
    Boolean existsByPhoneNumber(String phoneNumber);

    Optional<UserEntity> findById(Long userId);

    default UserResponse findUserResponseByUserId(Long userId) {
        Optional<UserEntity> userEntityOptional = findById(userId);
        UserEntity userEntity = userEntityOptional.get();
        return new UserResponse(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getPhoneNumber(),
                userEntity.getCreatedAt(),
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
                userEntity.getCreatedAt(),
                userEntity.isApproved());
    }

    @Query("SELECT u.id FROM UserEntity u WHERE u.isApproved = true")
    List<Long> getAllApprovedUserIds();

    default List<UserResponse> findAllUsers(){
        List<UserEntity> userEntityList = findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        for(UserEntity userEntity : userEntityList) {
            userResponseList.add (new UserResponse(
                    userEntity.getId(),
                    userEntity.getEmail(),
                    userEntity.getName(),
                    userEntity.getPhoneNumber(),
                    userEntity.getCreatedAt(),
                    userEntity.isApproved()));
        }
        return userResponseList;
    }
}
