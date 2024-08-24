package com.delta.delta.controller;

import com.delta.delta.entity.Image;
import com.delta.delta.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    // 새로운 이미지 생성
    @PostMapping
    public ResponseEntity<Image> createImage(@RequestParam Long postId, @RequestParam String fileName) {
        Image image = imageService.createImage(postId, fileName);
        return ResponseEntity.ok(image);
    }

    // postId 게시물의 모든 이미지 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Image>> getImagesByPostId(@PathVariable Long postId) {
        List<Image> images = imageService.getImagesByPostId(postId);
        return ResponseEntity.ok(images);
    }

    // imageId 이미지 조회
    @GetMapping("/{imageId}")
    public ResponseEntity<Image> getImageById(@PathVariable Long imageId) {
        Optional<Image> image = imageService.getImageById(imageId);
        return image.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // imageId 이미지 삭제
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }
}
