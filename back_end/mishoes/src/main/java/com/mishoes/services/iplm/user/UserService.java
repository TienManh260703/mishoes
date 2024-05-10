package com.mishoes.services.iplm.user;

import com.mishoes.dtos.requests.create.user.CreateUserRequest;
import com.mishoes.dtos.requests.update.user.UpdateUerRequest;
import com.mishoes.dtos.responses.user.UserResponse;
import com.mishoes.entity.User;
import com.mishoes.exceptions.DataNotFoundException;
import com.mishoes.mappers.user.UserMapper;
import com.mishoes.repositories.UserRepository;
import com.mishoes.services.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public User getUser(String id) throws DataNotFoundException {
        return userRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Cannot find user with id : " + id));
    }

    @Override
    public Page<UserResponse> getUsers(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest)
                .map(user -> userMapper.toUserResponse(
                        user));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return userRepository.save(
                userMapper.createUser(request)
        );
    }

    @Override
    public User updateUSer(String id, UpdateUerRequest request) throws DataNotFoundException {
        User existingUer = userRepository.findById(id).orElseThrow(() ->
             new DataNotFoundException("Cannot find user with id : " + id)
        );
        userMapper.updateUser(existingUer, request);
        return userRepository.save(existingUer);
    }

    @Override
    public String deleteUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setStatus(1);
            userRepository.save(user);
            return "Deleted user";
        } else {
            return "User not found. Delete failed";
        }
    }

}
