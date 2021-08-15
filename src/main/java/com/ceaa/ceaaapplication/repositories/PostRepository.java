package com.ceaa.ceaaapplication.repositories;

import com.ceaa.ceaaapplication.entities.Post;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {
    Iterable<Post> findAllByOrderByIdDesc();
}
