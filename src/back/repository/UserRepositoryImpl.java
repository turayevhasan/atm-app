package back.repository;

import back.model.User;
import back.repository.contracts.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {
    private static final UserRepository instance = new UserRepositoryImpl();

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        return instance;
    }

    private final List<User> users = new ArrayList<>();

    @Override
    public boolean add(User user) {
        return users.add(user);
    }

    @Override
    public boolean isUniqueUsername(String username) {
        for (User user : users) {
            if (user != null && user.getUsername().equals(username))
                return false;
        }
        return true;
    }


    @Override
    public User findByUsername(String username) {
        for (User user : users) {
            if (user != null && user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    @Override
    public User findByUserId(UUID id) {
        for (User user : users) {
            if (user != null && user.getId().equals(id))
                return user;
        }
        return null;
    }
}
