package br.com.draftpad.repository;

import br.com.draftpad.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
    User findByUserName(String userName);
}
