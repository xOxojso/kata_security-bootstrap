package ru.kata.spring.boot_security_bootstrap.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security_bootstrap.demo.details.UserDetailsImp;
import ru.kata.spring.boot_security_bootstrap.demo.entity.Role;
import ru.kata.spring.boot_security_bootstrap.demo.entity.User;
import ru.kata.spring.boot_security_bootstrap.demo.repo.RoleRepository;
import ru.kata.spring.boot_security_bootstrap.demo.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        return new UserDetailsImp(user.get());
    }

    public void save(User user) {
        Role userRole = roleRepository.findRoleByRoleName("ROLE_USER");
        user.addRole(userRole);
        userRole.addUser(user);
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.getById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public void updateUser(Long id, User updateUser) {
        User oldUser = userRepository.getById(id);
        oldUser.setUsername(updateUser.getUsername());
        oldUser.setAge(updateUser.getAge());
        oldUser.setEmail(updateUser.getEmail());
        oldUser.setRoles(updateUser.getRoles());
        userRepository.save(oldUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
