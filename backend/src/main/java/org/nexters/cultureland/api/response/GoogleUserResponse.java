package org.nexters.cultureland.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GoogleUserResponse {
    private String issued_to;
    private String audience;
    private String user_id;
    private String scope;
    private String expires_in;
    private String verified_email;
    private String access_type;
}
