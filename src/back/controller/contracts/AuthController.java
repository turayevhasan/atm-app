package back.controller.contracts;

import back.payload.SignInDTO;
import back.payload.SignUpDTO;
import back.payload.UserDTO;

public interface AuthController {
    UserDTO signIn(SignInDTO dto);
    UserDTO signUp(SignUpDTO dto);
}
