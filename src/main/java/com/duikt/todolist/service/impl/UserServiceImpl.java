    package com.duikt.todolist.service.impl;

    import com.duikt.todolist.entity.User;
    import com.duikt.todolist.exception.ResourceNotFoundException;
    import com.duikt.todolist.repository.UserRepository;
    import com.duikt.todolist.service.UserService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    @RequiredArgsConstructor
    public class UserServiceImpl implements UserService {
      private final UserRepository userRepo;

        @Override
        public void addUser(String name, String email) {
            User user = User.builder()
                    .name(name)
                    .email(email)
                    .build();
            userRepo.save(user);
        }

        @Override
        public User updateUser(Long id, String name, String email) {
            User user = userRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
            user.setName(name);
            user.setEmail(email);
            return userRepo.save(user);
        }

        @Override
        public User getUserById(Long id) {
            return userRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        }

        @Override
        public List<User> getAllUsers() {
            return userRepo.findAll();
        }

        @Override
        public boolean linkChatId(Long chatId, String email) {
            Optional<User> user = userRepo.findByEmail(email);
            user.ifPresent(u -> {
                u.setChatId(chatId);
                userRepo.save(u);
            });
            return user.isPresent();
        }

        @Override
        public void deleteUserById(Long id) {
            if (!userRepo.existsById(id)) {
                throw new ResourceNotFoundException("User not found with id: " + id);
            } else {
                userRepo.deleteById(id);
            }
        }
    }
