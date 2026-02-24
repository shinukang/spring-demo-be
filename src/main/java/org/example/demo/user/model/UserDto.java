package org.example.demo.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserDto {

    @Getter
    @AllArgsConstructor
    public static class SignupReq {
        private String name;
        private String email;
        private String password;

        public User toEntity() {
            return User.builder()
                    .name(this.name)
                    .email(this.email)
                    .password(this.password)
                    .build();
        }
    }
}
