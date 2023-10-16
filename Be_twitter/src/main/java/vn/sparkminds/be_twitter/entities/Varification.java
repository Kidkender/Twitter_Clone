package vn.sparkminds.be_twitter.entities;


import lombok.*;

import java.time.LocalDateTime;

//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//
//@Table(name = "t_varification")
@Data
public class Varification {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private boolean status = false;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String planType;

}
