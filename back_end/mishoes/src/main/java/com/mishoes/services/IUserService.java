package com.mishoes.services;

import com.mishoes.dtos.requests.create.user.CreateUserRequest;
import com.mishoes.dtos.requests.update.user.UpdateUerRequest;
import com.mishoes.entity.User;
import com.mishoes.exceptions.DataNotFoundException;

import java.util.List;

public interface IUserService {
    User getUser (String id) throws DataNotFoundException;
    List<User>  getUsers ();
    User createUser (CreateUserRequest request);
    User updateUSer(String id, UpdateUerRequest request) throws DataNotFoundException;
    String deleteUser (String id);
}
