package org.example.demo.verify;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.demo.user.UserRepository;
import org.example.demo.user.model.User;
import org.example.demo.verify.model.Verify;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyService {
    private final VerifyRepository verifyRepository;
    private final UserRepository userRepository;

    @Transactional
    public void verify(String uuid) {
        Verify verify = verifyRepository.findByUuid(uuid).orElseThrow();
        User user = userRepository.findByEmail(verify.getEmail()).orElseThrow();
        user.setEnable(true);
    }
}
