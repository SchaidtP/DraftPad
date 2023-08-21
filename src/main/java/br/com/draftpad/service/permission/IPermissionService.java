package br.com.draftpad.service.permission;

import br.com.draftpad.model.entity.Permission;

public interface IPermissionService {
    Permission getPermissionUser();

    Permission getPermissionModerator();
}
