package com.mishoes.controller.user;

import com.mishoes.dto.request.create.user.CreatePermissionRequest;
import com.mishoes.dto.response.APIResponse;
import com.mishoes.dto.response.user.PermissionResponse;
import com.mishoes.exception.ErrorCode;
import com.mishoes.service.iplm.user.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    public ResponseEntity<?> createPermission(@RequestBody CreatePermissionRequest request) {
        return ResponseEntity.ok().body(
                permissionService.createPermission(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<?>> getPermissions() {
        return ResponseEntity.ok().body(
                permissionService.getPermissions()
        );
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<?> deletePermission(@PathVariable String permissionId) {
        return ResponseEntity.ok().body(
                permissionService.deletePermission(permissionId)
        );
    }

}
