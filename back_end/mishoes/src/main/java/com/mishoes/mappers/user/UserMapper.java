package com.mishoes.mappers.user;

import com.mishoes.dtos.requests.create.user.CreateUserRequest;
import com.mishoes.dtos.requests.update.user.UpdateUerRequest;
import com.mishoes.dtos.responses.user.UserResponse;
import com.mishoes.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .code(user.getCode())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .gender(user.isGender())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }
    public User  createUser (CreateUserRequest request){
        // code ben servie
        return User.builder()
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .gender(request.isGender())
                .dateOfBirth(request.getDateOfBirth())
                .build();
    }
    public void  updateUser (User user, UpdateUerRequest request){
        user.setUserName(request.getUserName());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setGender(request.isGender());
        user.setDateOfBirth(request.getDateOfBirth());
    }
}
