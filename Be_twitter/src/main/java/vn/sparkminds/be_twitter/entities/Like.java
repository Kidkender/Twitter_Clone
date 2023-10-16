package vn.sparkminds.be_twitter.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_like")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Twit twit;


}
