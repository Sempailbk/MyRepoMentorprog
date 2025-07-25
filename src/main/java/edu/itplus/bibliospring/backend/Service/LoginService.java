package edu.itplus.bibliospring.backend.Service;

import edu.itplus.bibliospring.backend.model.User;

public interface LoginService {
    boolean login(User user);
    void register(User user);
}
