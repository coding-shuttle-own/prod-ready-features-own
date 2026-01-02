package com.anee.module4_prod_ready_features_own.prod_ready_features_own.controller;

import com.anee.module4_prod_ready_features_own.prod_ready_features_own.entities.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/audit")
public class AuditController {

    private final EntityManagerFactory entityManagerFactory;

    @GetMapping("/posts/{postId}")
    public List<PostEntity> getPostRevisions(@PathVariable Long postId) {
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());

        List<Number> revisions = reader.getRevisions(PostEntity.class, postId);
        return revisions.stream()
                .map(revisionNumber -> reader.find(PostEntity.class, postId, revisionNumber))
                .toList();
    }
}
