package com.mishoes.mapper.user;

import com.mishoes.dto.request.create.user.CreatePermissionRequest;
import com.mishoes.dto.response.user.PermissionResponse;
import com.mishoes.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

   public Permission toPermission(CreatePermissionRequest request) {
        return Permission.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

   public PermissionResponse toPermissionResponse(Permission permission) {
        return PermissionResponse.builder()
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }
}
