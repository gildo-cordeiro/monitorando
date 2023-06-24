package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "FROM User u WHERE u.login = :login and u.pass = :pass")
    Optional<User> findByUser(@Param("login") String login, @Param("pass") String pass);

}