package br.com.draftpad.service.permission;

import br.com.draftpad.model.entity.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> getPermissionUser();
}
