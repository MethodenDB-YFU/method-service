package de.yfu.intranet.methodendb.util;

import de.yfu.intranet.methodendb.models.User;
import de.yfu.intranet.methodendb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class UserObjectFactory {

    public static User anyAdmin() {
        final User user = new User();
        user.setId(UUID.randomUUID());
        user.setRole(User.Role.ADMIN);
        return user;
    }

    public static User anyEditor() {
        final User user = new User();
        user.setId(UUID.randomUUID());
        user.setRole(User.Role.EDITOR);
        return user;
    }

}
