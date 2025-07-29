package edu.itplus.bibliospring.backend.Service.impl;

import edu.itplus.bibliospring.backend.Utils.Impl.PasswordEncrypterSha256;
import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserRepository;
import edu.itplus.bibliospring.backend.Service.LoginService;
import edu.itplus.bibliospring.backend.Utils.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncrypter passwordEncrypter;

    @Override
    public boolean login(User user) {

        User dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser == null) {
            return false;
        } else if (dbUser.getPassword().equals(passwordEncrypter.hashPassword(user.getPassword(), dbUser.getUUID()))) {
            return true;
        }
        return false;
    }

    @Override
    public void register(User user) {
        user.setPassword(passwordEncrypter.hashPassword(user.getPassword(), user.getUUID()));
        userRepository.create(user);
    }
}
