package com.mishoes.mapper.user;

import com.mishoes.dto.request.create.user.CreateRoleRequest;
import com.mishoes.dto.response.user.RoleResponse;
import com.mishoes.entity.Permission;
import com.mishoes.entity.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleMapper {
    PermissionMapper permissionMapper;

    public RoleResponse toRoleResponse(Role role) {
        return RoleResponse.builder()
                .name(role.getName())
                .description(role.getDescription())
                .permissions(role.getPermissions().stream().map(
                        permission -> permissionMapper.toPermissionResponse(permission)
                ).collect(Collectors.toSet()))
                .build();
    }

    public Role toRole(CreateRoleRequest request) {

        return Role.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }
}
