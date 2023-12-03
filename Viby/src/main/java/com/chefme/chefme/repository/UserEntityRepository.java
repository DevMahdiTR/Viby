package com.chefme.chefme.repository;


import com.chefme.chefme.model.business.Business;
import com.chefme.chefme.model.user.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
@Transactional(readOnly = true)
public interface UserEntityRepository extends JpaRepository<UserEntity ,Integer> {
    @Query(value = "SELECT U FROM UserEntity  U WHERE U.id = :id")
    Optional<UserEntity> fetchUserWithId(@Param("id") UUID id);
    @Query(value = "SELECT U FROM UserEntity U WHERE  U.email = :email ")
    Optional<UserEntity> fetchUserWithEmail(@Param("email") String email);

    @Query(value = "SELECT U FROM UserEntity U where U.role.name != 'ADMIN' order by U.id ")
    List<UserEntity> fetchAllUsers(Pageable pageable);
    @Query(value = "SELECT EXISTS(SELECT U FROM UserEntity U WHERE  U.email = :email) AS RESULT")
    Boolean isEmailRegistered(@Param("email")String email);

    @Query(value = "SELECT EXISTS (SELECT U FROM UserEntity  U WHERE  U.userName = :username) AS RESULT")
    Boolean isUserNameRegistered(@Param("username")  String username);
    @Query(value = "SELECT COUNT(U) FROM UserEntity U")
    long getTotalUserEntityCount();
}
