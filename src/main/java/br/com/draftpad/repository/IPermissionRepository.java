package br.com.draftpad.repository;

import br.com.draftpad.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByDescription(String description);
}
