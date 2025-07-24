package org.app.gestion_meubles.mapper;

import org.app.gestion_meubles.model.dto.FurnitureDTO;
import org.app.gestion_meubles.model.entity.Furniture;

import java.util.List;

public class FurnitureMapper {

    public static FurnitureDTO userToUserDTO(Furniture furniture) {
        return new FurnitureDTO(Furniture.getId(), user.getFirstName(),  user.getLastName(), user.getAge());
    }

    public static List<UserDTO> usersToUserDTOs(List<User> users) {
        /*
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userToUserDTO(user));
        }
        return userDTOs;
        */

        return users.stream().map(UserMapper::userToUserDTO).toList();
    }

    public static User userDTOToUser(UserDTO userDTO) {
        return new User(null, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAge());
    }

    public static List<User> userDTOsToUsers(List<UserDTO> userDTOs) {
        return userDTOs.stream().map(UserMapper::userDTOToUser).toList();
    }
}
