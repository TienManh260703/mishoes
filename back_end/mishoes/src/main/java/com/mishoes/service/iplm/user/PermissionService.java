package com.mishoes.service.iplm.user;

import com.mishoes.dto.request.create.user.CreatePermissionRequest;
import com.mishoes.dto.response.user.PermissionResponse;
import com.mishoes.entity.Permission;
import com.mishoes.mapper.user.PermissionMapper;
import com.mishoes.repository.PermissionRepository;
import com.mishoes.service.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService implements IPermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse createPermission(CreatePermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(
                permissionRepository.save(permission)
        );
    }

    @Override
    public List<PermissionResponse> getPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    @Override
    public String deletePermission(String name) {
        Optional<Permission> optionalPermission = permissionRepository.findById(name);
        if(optionalPermission.isPresent()){
            permissionRepository.deleteById(name);
            return "Permission Deleted";
        }
        return "Delete permission failed";
    }
}
