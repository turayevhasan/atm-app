package back.payload;

import java.util.UUID;

public record UserDTO(UUID id, String firstName, String lastName, String username, boolean isActive) {

}
