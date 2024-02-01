package com.stepanew.exam.questionnaire.store.repositories;

import com.stepanew.exam.questionnaire.store.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {



}
