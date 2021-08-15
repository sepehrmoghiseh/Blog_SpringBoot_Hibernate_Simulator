package com.pazandish.weblog.repository;

import com.pazandish.weblog.domain.UsersEntity;
import com.pazandish.weblog.domain.enumeration.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
    @Modifying
    @Query("update UsersEntity user set user.role=:role2 where user.id=:id2")
    @Transactional
    int setRoleAdmin(@Param("id2") int id2, @Param("role2") Role role2);

    @Modifying
    @Query("update UsersEntity user set user.role=:role2 where user.id=:id2")
    @Transactional
    int setRoleSender(@Param("id2") int id2, @Param("role2") Role role2);

    Optional<UsersEntity> findByUserName(String userName);

    @Query("update UsersEntity user set user.blocked=:situation where user.id=:id2")
    @Transactional
    @Modifying
    int block(@Param("situation") boolean situation, @Param("id2") int id2);


}
