package com.ceaa.ceaaapplication.services;

import com.ceaa.ceaaapplication.entities.Post;

import java.util.List;

public interface PostService {
    Iterable<Post> getAllPosts();
    Iterable<Post> getAllPosts(int pageNum, int postsPerPage);
    Post createPost(Post post);
    Post getPostById(int id);
    Boolean deletePostById(int id);
    Post updatePostById(int id, Post postReq);
    Iterable<Post> saveAllPosts(List<Post> posts);
}
