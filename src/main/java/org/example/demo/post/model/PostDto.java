package org.example.demo.post.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ListRes {
        private List<ReadRes> list;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class UpdateReq {
        private Long id;
        private String title;
        private String contents;
    }
}
