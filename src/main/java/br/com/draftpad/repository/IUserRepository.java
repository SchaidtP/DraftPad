package br.com.draftpad.repository;

import br.com.draftpad.domain.user.User;
import br.com.draftpad.domain.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
    UserDetails findByUserName(String userName);

    Optional<List<User>> findAllByRole (UserRole role);
}
