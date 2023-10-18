package vn.sparkminds.be_twitter.services.dto;

import lombok.Data;
import vn.sparkminds.be_twitter.entities.User;

@Data
public class LikeDto {
    private Long id;
    private UserDto user;
    private TwitDto twit;

}
