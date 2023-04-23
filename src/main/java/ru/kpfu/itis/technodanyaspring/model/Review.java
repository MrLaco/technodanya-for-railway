package ru.kpfu.itis.technodanyaspring.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "reviews")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
