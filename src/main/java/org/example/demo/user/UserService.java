package org.example.demo.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.demo.user.model.AuthUserDetails;
import org.example.demo.user.model.User;
import org.example.demo.user.model.UserDto;
import org.example.demo.verify.VerifyRepository;
import org.example.demo.verify.model.Verify;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final VerifyRepository verifyRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserDto.SignupReq dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(dto.toEntity());

        String uuid = UUID.randomUUID().toString();
        Verify verify = Verify.builder()
                .email(dto.getEmail())
                .uuid(uuid)
                .build();

        verifyRepository.save(verify);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User entity = userRepository.findByEmail(username).orElseThrow();
        return AuthUserDetails.fromEntity(entity);
    }
}
