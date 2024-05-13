package com.mishoes.service;

import com.mishoes.dto.request.create.user.CreatePermissionRequest;
import com.mishoes.dto.response.user.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse createPermission (CreatePermissionRequest request);
    List<PermissionResponse> getPermissions ();
    String deletePermission (String name);
}
