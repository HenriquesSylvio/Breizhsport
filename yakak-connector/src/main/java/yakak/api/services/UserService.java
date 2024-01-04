package yakak.api.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import yakak.api.models.Role;
import yakak.api.models.entities.User;

@ApplicationScoped
public class UserService {

    @Transactional
    public User createUser(String firstName, String lastName, String password, String email, Role role) {
        User user = new User(firstName, lastName, password, email, role);
        user.persist();
        return user;
    }

    public List<User> getAllUsers() {
        return User.listAll();
    }

    public User getUserById(Long id) {
        return User.findById(id);
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return User.deleteById(id);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User user = User.findById(id);
        if (user != null) {
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
        }
        return user;
    }
}
