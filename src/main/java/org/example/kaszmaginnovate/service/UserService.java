package org.example.kaszmaginnovate.service;

import org.example.kaszmaginnovate.model.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    boolean authenticate(String username, String password);

    void save(User user);
}
