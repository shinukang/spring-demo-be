package org.example.demo.verify;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.demo.user.UserRepository;
import org.example.demo.user.model.User;
import org.example.demo.user.model.UserDto;
import org.example.demo.verify.model.Verify;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerifyService {
    private final VerifyRepository verifyRepository;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    public void sendMail(UserDto.SignupReq dto) throws MessagingException {
        String uuid = UUID.randomUUID().toString();
        Verify verify = Verify.builder()
                .email(dto.getEmail())
                .uuid(uuid)
                .build();
        verifyRepository.save(verify);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(dto.getEmail());
        helper.setSubject("인증 메일입니다.");
        String htmlText = "<a href=\"http://localhost:8080/verify/email?uuid="+uuid+"\">이메일 인증하기</a>";
        helper.setText(htmlText, true);
        javaMailSender.send(mimeMessage);
    }
    @Transactional
    public void verify(String uuid) {
        Verify verify = verifyRepository.findByUuid(uuid).orElseThrow();
        User user = userRepository.findByEmail(verify.getEmail()).orElseThrow();
        user.setEnable(true);
        verifyRepository.delete(verify);
    }
}
