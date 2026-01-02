package com.anee.module4_prod_ready_features_own.prod_ready_features_own.services;

import com.anee.module4_prod_ready_features_own.prod_ready_features_own.dto.PostDTO;
import com.anee.module4_prod_ready_features_own.prod_ready_features_own.entities.PostEntity;
import com.anee.module4_prod_ready_features_own.prod_ready_features_own.exceptions.ResourceNoteFoundException;
import com.anee.module4_prod_ready_features_own.prod_ready_features_own.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNoteFoundException("Post not found with id: " + postId)
        );

        return modelMapper.map(postEntity, PostDTO.class);
    }
}
