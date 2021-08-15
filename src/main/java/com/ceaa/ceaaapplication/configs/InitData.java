package com.ceaa.ceaaapplication.configs;

import com.ceaa.ceaaapplication.entities.Post;
import com.ceaa.ceaaapplication.services.PostService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.Random;

@Configuration
public class InitData {
    Random random = new Random();
    List<String> authors = List.of("Anshuman Panda", "Joe Hahn", "Mike Shinoda", "Rob Bourdon", "Dave Farrell", "Brad Delson");
    List<String> categories = List.of("Health", "Books", "Technology", "Cooking", "Travel", "Fashion", "Art", "Music");
    Long postDate = Long.parseLong("1627029900000");

    @Bean
    CommandLineRunner runner(PostService postService) {
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Post>> typeReference = new TypeReference<List<Post>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/posts.json");
            try {
                List<Post> posts = objectMapper.readValue(inputStream, typeReference);
                for (Post p : posts) {
                    p.setAuthor(authors.get(random.nextInt(authors.size())));
                    p.setCategory(categories.get(random.nextInt(categories.size())));
//                    p.setCreatedAt(new Date(postDate));
//                    postDate = postDate + 10800000;
                }
                postService.saveAllPosts(posts);
                System.out.println("All posts saved to Database.");
            }
            catch (Exception e) {
                System.out.println("Unable to save posts: " + e.getMessage());
            }
        };
    }
}
