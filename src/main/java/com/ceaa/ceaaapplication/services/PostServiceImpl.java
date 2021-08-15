package com.ceaa.ceaaapplication.services;

import com.ceaa.ceaaapplication.entities.Post;
import com.ceaa.ceaaapplication.repositories.PostRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Cacheable(value = "posts")
    public Iterable<Post> getAllPosts() {
        return postRepository.findAllByOrderByIdDesc();
    }

    @Override
    @Cacheable(value = "posts")
    public Iterable<Post> getAllPosts(int pageNum, int postsPerPage) {
        Pageable pagedPosts = PageRequest.of(pageNum, postsPerPage, Sort.by("id").descending());
        return postRepository.findAll(pagedPosts);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    @Cacheable(value = "postWithId")
    public Post getPostById(int id) {
        Optional<Post> postOptional = postRepository.findById(id);
        return postOptional.orElse(null);
    }

    @Override
    public Boolean deletePostById(int id) {
        try {
            postRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public Post updatePostById(int id, Post postReq) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setTitle(postReq.getTitle());
            post.setAuthor(postReq.getAuthor());
            post.setCreatedAt(postReq.getCreatedAt());
            post.setCategory(postReq.getCategory());
            post.setBody(postReq.getBody());
            return postRepository.save(post);
        }
        return null;
    }

    @Override
    public Iterable<Post> saveAllPosts(List<Post> posts) {
        return postRepository.saveAll(posts);
    }
}
