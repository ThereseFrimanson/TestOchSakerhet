package se.yrgo.libraryapp.services;

import java.util.Optional;
import javax.inject.Inject;

import org.pac4j.core.credentials.password.PasswordEncoder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
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

    public Optional<UserId> validate(String username, String password) {
        Optional<LoginInfo> maybeLoginInfo = userDao.getLoginInfo(username);
        if (maybeLoginInfo.isEmpty()) {
            return Optional.empty();
        }

        LoginInfo loginInfo = maybeLoginInfo.get();

        // Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        // if (!encoder.matches(password, loginInfo.getPasswordHash())) {
        //     return Optional.empty();
        // }

        return Optional.of(loginInfo.getUserId());
    }        
}
