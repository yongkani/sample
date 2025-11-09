package com.example.sample.service;

import com.example.sample.entity.Gallery;
import com.example.sample.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GalleryService {

    private final GalleryRepository galleryRepository;

    public List<Gallery> getAllGalleries() {
        return galleryRepository.findAllByOrderByDisplayOrderAsc();
    }

    public Page<Gallery> getAllGalleries(Pageable pageable) {
        return galleryRepository.findAllByOrderByDisplayOrderAsc(pageable);
    }

    public Gallery getGalleryById(Long id) {
        return galleryRepository.findById(id).orElse(null);
    }

    @Transactional
    public Gallery saveGallery(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Transactional
    public void deleteGallery(Long id) {
        galleryRepository.deleteById(id);
    }
}
