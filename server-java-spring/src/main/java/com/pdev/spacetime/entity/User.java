package com.pdev.spacetime.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "`user`")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "git_hub_id")
    private Long gitHubId;

    private String name;

    private String login;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @OneToMany
    @JoinColumn(name = "`user_id`")
    @ToString.Exclude
    @JsonManagedReference
    private List<Memory> memories;

    public User(Long id) {
        this.id = id;
    }

}
