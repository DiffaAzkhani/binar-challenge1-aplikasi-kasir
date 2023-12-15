package binar.bej1.diffaazkhani.BinarFudChallenge4.repository;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.Roles;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.enums.UserRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRoleName(UserRoleModel name);
}
