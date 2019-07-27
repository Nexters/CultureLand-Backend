package org.nexters.cultureland.api.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UserDto {
    private Long userId;
    private String userName;
    private String accessToken;

    public UserDto(Long userId, String userName, String accessToken) {
        this.userId = userId;
        this.userName = userName;
        this.accessToken = accessToken;
    }
}
