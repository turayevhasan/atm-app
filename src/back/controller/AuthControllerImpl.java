package back.controller;

import back.controller.contracts.AuthController;
import back.model.User;
import back.payload.SignInDTO;
import back.payload.SignUpDTO;
import back.payload.UserDTO;
import back.repository.contracts.UserRepository;
import back.repository.UserRepositoryImpl;
import back.utils.Utils;

public class AuthControllerImpl implements AuthController {
    private static final AuthController instance = new AuthControllerImpl();

    private AuthControllerImpl() {
    }

    public static AuthController getInstance() {
        return instance;
    }

    private final UserRepository userRepository = UserRepositoryImpl.getInstance();

    @Override
    public UserDTO signIn(SignInDTO dto) {
        User user = userRepository.findByUsername(dto.username());

        if (user == null) {
            throw new RuntimeException("User not found ❌");
        }
        if (!user.getPassword().equals(dto.password())) {
            throw new RuntimeException("Password is incorrect ❌");
        }

        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.isActive());
    }

    @Override
    public UserDTO signUp(SignUpDTO dto) {
        boolean isUniqueUsername = userRepository.isUniqueUsername(dto.username());

        if (!isUniqueUsername)
            throw new RuntimeException("This username is already found ❌");

        boolean isValidUserPassword = Utils.isValidUserPassword(dto.password());

        if (!isValidUserPassword) {
            throw new RuntimeException("User password length must be 8 or upper ❌");
        }
        User user = new User(dto.firstName(), dto.lastName(), dto.username(), dto.password(), true);
        userRepository.add(user);
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.isActive());
    }
}
