package com.mishoes.service;

import com.mishoes.dto.request.create.user.CreateRoleRequest;
import com.mishoes.dto.response.user.RoleResponse;

public interface IRoleService {

    RoleResponse createRole(CreateRoleRequest request);
}
