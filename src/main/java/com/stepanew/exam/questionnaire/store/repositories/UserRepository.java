package com.stepanew.exam.questionnaire.store.repositories;

import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Page<UserEntity> findAllByUsernameContainingIgnoreCase(String username, Pageable pageable);

}
