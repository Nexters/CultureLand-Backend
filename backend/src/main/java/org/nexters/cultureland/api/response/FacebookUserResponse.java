package org.nexters.cultureland.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FacebookUserResponse {
    private long id;
    private String name;
    private String email;
}
