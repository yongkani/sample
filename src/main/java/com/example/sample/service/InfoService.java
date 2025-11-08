package com.example.sample.service;

import com.example.sample.entity.Info;
import com.example.sample.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
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
