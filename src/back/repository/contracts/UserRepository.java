package back.repository.contracts;

import back.model.User;

import java.util.UUID;

public interface UserRepository {
    boolean add(User user);
    boolean isUniqueUsername(String username);
    User findByUsername(String username);

    User findByUserId(UUID id);

}
