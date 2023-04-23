package ru.kpfu.itis.technodanyaspring.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "images")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    private Article article;
}
