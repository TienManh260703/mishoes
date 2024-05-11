package com.mishoes.controller.user;

import com.mishoes.dto.request.create.user.CreateUserRequest;
import com.mishoes.dto.request.update.user.UpdateUerRequest;
import com.mishoes.dto.response.user.UserResponse;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.mapper.user.UserMapper;
import com.mishoes.service.iplm.user.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) throws DataNotFoundException {
        return ResponseEntity.ok().body(
                userMapper.toUserResponse(userService.getUser(id))
        );
    }

    @GetMapping
    public ResponseEntity<?> getUsers(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        PageRequest pageRequest = PageRequest.of(page, limit, Sort.by("createdAt"));
        Page<UserResponse> userPage = userService.getUsers(pageRequest);
        // chỉnh sửa khi có FE
        int totalPage = userPage.getTotalPages();
        List<UserResponse> users = userPage.getContent();
        return ResponseEntity.ok().body(
                users
        );
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok().body(
                userService.createUser(request)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UpdateUerRequest request)  {
        return ResponseEntity.ok().body(
                userMapper.toUserResponse(
                        userService.updateUSer(id, request)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok().body(
                userService.deleteUser(id)
        );
    }


}
