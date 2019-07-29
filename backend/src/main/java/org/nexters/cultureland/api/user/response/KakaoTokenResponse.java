package org.nexters.cultureland.api.user.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString @NoArgsConstructor
public class KakaoTokenResponse {
    private Long id;
    private String expiresInMillis;
    private String appId;
}
