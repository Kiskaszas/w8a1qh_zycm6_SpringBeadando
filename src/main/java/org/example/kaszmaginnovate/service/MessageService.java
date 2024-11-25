package org.example.kaszmaginnovate.service;

import org.example.kaszmaginnovate.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface MessageService {
    Optional<List<Message>> findAll();
    Optional<Message> findById(Long id);
    void delete(Long id);
    Page<Message> getAllMessage(Pageable of);

    void save(Message message);
}
