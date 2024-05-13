package com.mishoes.mapper.user;

import com.mishoes.dto.request.create.user.CreateUserRequest;
import com.mishoes.dto.request.update.user.UpdateUerRequest;
import com.mishoes.dto.response.user.UserResponse;
import com.mishoes.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.mishoes.common.GenCode.generateUSER;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setCode(user.getCode());
        response.setUserName(user.getUserName());

        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setAddress(user.getAddress());
        response.setGender(user.isGender());
        response.setDateOfBirth(user.getDateOfBirth());
        response.setCreateAt(user.getCreatedAt());
        response.setUpdateAt(user.getUpdatedAt());
//        response.setRoles(user.getRoles());
        response.setStatus(user.getStatus());
        return response;
    }

    public User createUser(CreateUserRequest request) {
        // code ben servie
        return User.builder()
                .code(generateUSER())
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .gender(request.isGender())
                .dateOfBirth(request.getDateOfBirth())
                .build();
    }

    public void updateUser(User user, UpdateUerRequest request) {
        user.setUserName(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setGender(request.isGender());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setUpdatedAt(LocalDateTime.now());
    }
}
