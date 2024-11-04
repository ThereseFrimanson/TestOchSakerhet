package se.yrgo.libraryapp.services;

import java.util.Optional;
import javax.inject.Inject;
import org.springframework.security.crypto.password.PasswordEncoder;

import se.yrgo.libraryapp.dao.UserDao;
import se.yrgo.libraryapp.entities.*;

public class UserService {
    private UserDao userDao;
    private PasswordEncoder encoder;

    @Inject
    UserService(UserDao userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    private String hashPassword(String password) {
        return encoder.encode(password);
    }

    public boolean register(String name, String realName, String password) {
        if(!userDao.isNameAvailable(name)){
            return false;
        }
        String passwordHash = encoder.encode(password);
        return userDao.register(name, realName, passwordHash);

    }

    public Optional<UserId> validate(String username, String password) {
        Optional<LoginInfo> maybeLoginInfo = userDao.getLoginInfo(username);
        if (maybeLoginInfo.isEmpty()) {
            return Optional.empty();
        }

        LoginInfo loginInfo = maybeLoginInfo.get();

        if (!encoder.matches(password, loginInfo.getPasswordHash())) {
            return Optional.empty();
        }

        return Optional.of(loginInfo.getUserId());
    }
}
