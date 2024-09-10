package com.socialmedia.posts.repository;

import com.socialmedia.posts.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAll();
    Optional<PostEntity> findById(Long id);

    Page<PostEntity> findAllByUsername(String username, Pageable pageable);

    Optional<PostEntity> findByUsernameAndPostId(String username, Long id);
}
