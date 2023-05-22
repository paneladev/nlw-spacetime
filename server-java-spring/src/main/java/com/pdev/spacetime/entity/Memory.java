package com.pdev.spacetime.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "memory")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Memory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonBackReference
    private User user;
}
