package org.example.demo.verify;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/verify")
public class VerifyController {
    private final VerifyService verifyService;

    @GetMapping("/email")
    public ResponseEntity verify(@RequestParam String uuid) {
        verifyService.verify(uuid);
        return ResponseEntity.ok("이메일 인증 완료");
    }
}
