package vn.sparkminds.be_twitter.services.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String image;
    private String location;
    private String website;
    private String birthDate;
    private String mobile;
    private String backGroundImage;
    private String bio;
    private boolean req_user;
    private boolean loginWithGoogle;
    private List<UserDto> followers=new ArrayList<>();
    private List<UserDto> followings=new ArrayList<>();
    private boolean followed;
    private boolean isVerified;
}
