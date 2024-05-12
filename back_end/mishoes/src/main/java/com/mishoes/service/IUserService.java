package com.mishoes.service;

import com.mishoes.dto.request.create.user.CreateUserRequest;
import com.mishoes.dto.request.update.user.UpdateUerRequest;
import com.mishoes.dto.response.user.UserResponse;
import com.mishoes.entity.User;
import com.mishoes.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IUserService {
    User getUser (String id) throws DataNotFoundException;
    Page<UserResponse> getUsers (PageRequest pageRequest);
    User createUser (CreateUserRequest request);
    User updateUSer(String id, UpdateUerRequest request) throws DataNotFoundException;
    String deleteUser (String id);

    UserResponse getMyInfo ();
}
