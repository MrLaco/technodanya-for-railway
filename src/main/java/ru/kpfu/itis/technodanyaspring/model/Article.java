package ru.kpfu.itis.technodanyaspring.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity(name = "articles")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false, length = 10000)
    private String content;

    // чтоб доставать ссылку на картинки
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

    @Column(nullable = false)
    private String date;

    // чтоб доставать отсюда email юзера
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", user=" + (user != null ? user.getEmail() : null) +
                '}';
    }
}
