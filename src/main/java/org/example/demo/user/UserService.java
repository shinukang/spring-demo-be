package org.example.demo.user;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.demo.user.model.AuthUserDetails;
import org.example.demo.user.model.User;
import org.example.demo.user.model.UserDto;
import org.example.demo.verify.VerifyRepository;
import org.example.demo.verify.VerifyService;
import org.example.demo.verify.model.Verify;
import org.springframework.mail.javamail.JavaMailSender;
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
    private final VerifyService verifyService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserDto.SignupReq dto) throws MessagingException {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(dto.toEntity());
        verifyService.sendMail(dto);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User entity = userRepository.findByEmail(username).orElseThrow();
        return AuthUserDetails.fromEntity(entity);
    }
}
