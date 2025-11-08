package com.example.sample.service;

import com.example.sample.entity.Info;
import com.example.sample.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InfoService {

    private final InfoRepository infoRepository;

    public List<Info> getAllInfo() {
        return infoRepository.findAll();
    }

    public Page<Info> getInfoPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return infoRepository.findAll(pageable);
    }

    public Info getInfoById(Long id) {
        return infoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Info saveInfo(Info info) {
        return infoRepository.save(info);
    }

    @Transactional
    public void deleteInfo(Long id) {
        infoRepository.deleteById(id);
    }
}
