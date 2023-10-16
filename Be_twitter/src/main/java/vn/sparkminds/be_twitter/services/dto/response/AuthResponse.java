package vn.sparkminds.be_twitter.services.dto.response;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthResponse {
    private String jwt;
    private boolean status;
}
