package org.example.kaszmaginnovate.service;

import org.example.kaszmaginnovate.model.Message;
import org.example.kaszmaginnovate.repository.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<List<Message>> findAll() {
        return Optional.of(messageRepository.findAll());
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Page<Message> getAllMessage(Pageable of) {
        return messageRepository.findAll(of);
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }
}
