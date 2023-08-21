package br.com.draftpad.service.permission;

import br.com.draftpad.model.entity.Permission;
import br.com.draftpad.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository repository;

    @Override
    public Permission getPermissionUser() {
        return repository.findByDescription("USER");
    }

    @Override
    public Permission getPermissionModerator() {
        return repository.findByDescription("MODERATOR");
    }
}