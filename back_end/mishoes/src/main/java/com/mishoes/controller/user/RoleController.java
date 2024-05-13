package com.mishoes.controller.user;

import com.mishoes.dto.request.create.user.CreatePermissionRequest;
import com.mishoes.dto.request.create.user.CreateRoleRequest;
import com.mishoes.service.iplm.user.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleService roleService;

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody CreateRoleRequest request) {
        return ResponseEntity.ok().body(
                roleService.createRole(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<?>> getRoles() {
        return ResponseEntity.ok().body(
               roleService.getRoles()
        );
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable String roleId) {
        return ResponseEntity.ok().body(
               roleService.deleteRole(roleId)
        );
    }
}
