package binar.bej1.diffaazkhani.BinarFudChallenge4.repository;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Long> {
    Optional<UsersModel> findUserByUsernameAndPassword(String userId, String password);

    Optional<UsersModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmailAddress(String email);
}
