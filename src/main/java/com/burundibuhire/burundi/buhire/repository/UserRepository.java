package com.burundibuhire.burundi.buhire.repository;

import com.burundibuhire.burundi.buhire.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByNomUtilisateur(String nomUtilisateur);
    //Optional<User> findByEmail(String email);
    Optional<User> findUserByEmail(String email);
    User findUsersByMemberIdNumber(String memberIdNumber);
    Optional<User> findByVerificationToken(String token);

}
