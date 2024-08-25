package com.delta.delta.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String profileImage;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "user_followers", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "follower_id")
    private Set<Long> followers = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "user_followings", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "following_id")
    private Set<Long> followings = new HashSet<>();


    //ignore for test
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    @JsonIgnoreProperties({"comments", "postLikes", "images"})
    private List<Post> posts;
}
