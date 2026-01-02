package com.anee.module4_prod_ready_features_own.prod_ready_features_own.services;

import com.anee.module4_prod_ready_features_own.prod_ready_features_own.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO postDTO);

    PostDTO getPostById(Long postId);
}
