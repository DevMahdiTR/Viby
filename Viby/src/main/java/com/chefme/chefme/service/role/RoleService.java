package com.chefme.chefme.service.role;


import com.chefme.chefme.model.role.Role;

public interface RoleService {

    public Role fetchRoleByName(final String roleName);
}
