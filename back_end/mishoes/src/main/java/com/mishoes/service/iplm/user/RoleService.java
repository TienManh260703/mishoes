package com.mishoes.service.iplm.user;

import com.mishoes.dto.request.create.user.CreateRoleRequest;
import com.mishoes.dto.response.user.RoleResponse;
import com.mishoes.entity.Permission;
import com.mishoes.entity.Role;
import com.mishoes.exception.DataAlreadyExistsException;
import com.mishoes.mapper.user.RoleMapper;
import com.mishoes.repository.PermissionRepository;
import com.mishoes.repository.RoleRepository;
import com.mishoes.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(CreateRoleRequest request) {
        if (roleRepository.existsByName(request.getName())) {
            new DataAlreadyExistsException("Role already exist");
        }
        Role role = roleMapper.toRole(request);
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }


    public List<RoleResponse> getRoles() {
        return roleRepository.findAll().stream().map(
                role -> roleMapper.toRoleResponse(role)
        ).collect(Collectors.toList());
    }

    // Sau chuyển thành trạng thái
    public String deleteRole(String roleName) {
        Optional<Role> roleOptional = roleRepository.findById(roleName);
        if (roleOptional.isPresent()) {
            roleRepository.deleteById(roleName);
            return "Deleted role" + roleName;
        }
        return "Delete role " + roleName + " fail";
    }
}
