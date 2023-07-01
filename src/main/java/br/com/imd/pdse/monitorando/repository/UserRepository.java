package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "FROM User u WHERE u.username = :login and u.password = :password" )
    Optional<User> findByUser(@Param("login" ) String login, @Param("password" ) String pass);

    @Query(value = "FROM User u WHERE u.username = :email" )
    User findByUsername(String email);

    @Query(value = "SELECT c FROM Teacher t INNER JOIN t.user u INNER JOIN t.classrooms c WHERE u.userType = :userType AND u.uuid = :id AND c.active = true" )
    List<Classroom> findByIdAndUserType(@Param("userType" ) UserType userType, @Param("id" ) UUID id);

}
