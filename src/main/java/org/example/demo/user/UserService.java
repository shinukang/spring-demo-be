package org.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.example.demo.user.model.User;
import org.example.demo.user.model.UserDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signup(UserDto.SignupReq dto) {
        userRepository.save(dto.toEntity());
    }

    public UserDto.LoginRes login(UserDto.LoginReq dto) {
        User user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()).orElseThrow();
        return UserDto.LoginRes.fromEntity(user);
    }
}
