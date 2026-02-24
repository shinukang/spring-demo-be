package org.example.demo.user;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.demo.user.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserDto.SignupReq dto) throws MessagingException {
        userService.signup(dto);
        return ResponseEntity.ok("회원가입 성공");
    }
}
