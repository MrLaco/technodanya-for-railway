package ru.kpfu.itis.technodanyaspring.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "premiums")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Premium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private String purchaseDate;

    @OneToOne
    private User user;
}
