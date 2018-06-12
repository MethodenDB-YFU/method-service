package de.yfu.intranet.methods.util;

import de.yfu.intranet.methods.data.domain.User;

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
