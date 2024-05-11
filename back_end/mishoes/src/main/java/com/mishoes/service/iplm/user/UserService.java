package com.mishoes.service.iplm.user;

import com.mishoes.dto.request.create.user.CreateUserRequest;
import com.mishoes.dto.request.update.user.UpdateUerRequest;
import com.mishoes.dto.response.user.UserResponse;
import com.mishoes.entity.User;
import com.mishoes.enums.Role;
import com.mishoes.exception.DataAlreadyExistsException;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.mapper.user.UserMapper;
import com.mishoes.repository.UserRepository;
import com.mishoes.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    @Override
    public User getUser(String id)  {
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
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new DataAlreadyExistsException("User name already exist");
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new DataAlreadyExistsException("Phone number already exist");
        }
        User user = userMapper.createUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        return userRepository.save(
                user
        );
    }

    @Override
    public User updateUSer(String id, UpdateUerRequest request) throws DataNotFoundException {
        User existingUer = userRepository.findById(id).orElseThrow(() -> {
                    throw new DataNotFoundException("Cannot find user with id : " + id);
                }
        );
        // Xử lý trùng username và password ?? -> chưa làm

        userMapper.updateUser(existingUer, request);
        existingUer.setPassword(passwordEncoder.encode(request.getPassword()));
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
