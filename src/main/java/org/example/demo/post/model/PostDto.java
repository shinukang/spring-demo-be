package org.example.demo.post.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class PostDto {

    @Getter
    @AllArgsConstructor
    public static class CreateReq {
        private String title;
        private String contents;

        public Post toEntity() {
            return Post.builder()
                    .title(this.title)
                    .contents(this.contents)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ReadRes {
        private Long id;
        private String title;
        private String contents;

        public static ReadRes fromEntity(Post entity) {
            return ReadRes.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .build();
        }
    }
}
