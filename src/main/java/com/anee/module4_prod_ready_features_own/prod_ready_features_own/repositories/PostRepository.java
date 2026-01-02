package com.anee.module4_prod_ready_features_own.prod_ready_features_own.repositories;

import com.anee.module4_prod_ready_features_own.prod_ready_features_own.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
