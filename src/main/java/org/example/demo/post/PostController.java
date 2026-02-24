package org.example.demo.post;

import lombok.RequiredArgsConstructor;
import org.example.demo.post.model.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody PostDto.CreateReq dto) {
        postService.create(dto);
        return ResponseEntity.ok("게시글 작성 성공");
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PostDto.ReadRes> read(@PathVariable Long id) {
        PostDto.ReadRes res = postService.read(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/list")
    public ResponseEntity<PostDto.ListRes> list() {
        PostDto.ListRes res = postService.list();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.ok("게시글 삭제 성공");
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody PostDto.UpdateReq dto) {
        postService.update(dto);
        return ResponseEntity.ok("게시글 수정 성공");
    }
}
