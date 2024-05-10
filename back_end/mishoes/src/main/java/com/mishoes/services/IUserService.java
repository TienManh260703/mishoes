package com.mishoes.services;

import com.mishoes.dtos.requests.create.user.CreateUserRequest;
import com.mishoes.dtos.requests.update.user.UpdateUerRequest;
import com.mishoes.dtos.responses.user.UserResponse;
import com.mishoes.entity.User;
import com.mishoes.exceptions.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IUserService {
    User getUser (String id) throws DataNotFoundException;
    Page<UserResponse> getUsers (PageRequest pageRequest);
    User createUser (CreateUserRequest request);
    User updateUSer(String id, UpdateUerRequest request) throws DataNotFoundException;
    String deleteUser (String id);
}
