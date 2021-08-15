package com.ceaa.ceaaapplication.controllers;

import com.ceaa.ceaaapplication.entities.Post;
import com.ceaa.ceaaapplication.hooks.HashGeneratorHook;
import com.ceaa.ceaaapplication.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@CrossOrigin(value = "*")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    ResponseEntity<Object> getAllPosts(@RequestParam(required = false) Optional<Integer> pageNum, @RequestParam(required = false) Optional<Integer> postsPerPage) {
        try {
            if (pageNum.isPresent() && postsPerPage.isPresent()) {
                return new ResponseEntity<>(postService.getAllPosts(pageNum.get(), postsPerPage.get()), HttpStatus.OK);
            }
            return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/posts")
    ResponseEntity<Object> createPost(@RequestBody Post post) {
        try {
            return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts/{id}")
    ResponseEntity<Object> getPostById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/posts/{id}")
    ResponseEntity<Object> deletePostById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(postService.deletePostById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/posts/{id}")
    ResponseEntity<Object> updatePostById(@PathVariable int id, @RequestBody Post post) {
        try {
            return new ResponseEntity<>(postService.updatePostById(id, post), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getHash")
    ResponseEntity<Object> getHash() {
        HashMap<String, String> currentHash = new HashMap<>();
        currentHash.put("hash", HashGeneratorHook.getCalculatedHash());
        return new ResponseEntity<>(currentHash, HttpStatus.OK);
    }

}
