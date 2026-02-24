package org.example.demo.post;

import lombok.RequiredArgsConstructor;
import org.example.demo.post.model.Post;
import org.example.demo.post.model.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void create(PostDto.CreateReq dto) {
        postRepository.save(dto.toEntity());
    }
    public PostDto.ReadRes read(Long id) {
        Post entity = postRepository.findById(id).orElseThrow();
        return PostDto.ReadRes.fromEntity(entity);
    }
    public PostDto.ListRes list() {
        List<Post> entities = postRepository.findAll();
        return PostDto.ListRes.builder()
                .list(entities.stream().map(PostDto.ReadRes::fromEntity).toList())
                .build();
    }
    public void delete(Long id) {
        Post entity = postRepository.findById(id).orElseThrow();
        postRepository.delete(entity);
    }
}
