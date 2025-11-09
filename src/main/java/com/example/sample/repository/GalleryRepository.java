package com.example.sample.repository;

import com.example.sample.entity.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    List<Gallery> findAllByOrderByDisplayOrderAsc();
    Page<Gallery> findAllByOrderByDisplayOrderAsc(Pageable pageable);
}
