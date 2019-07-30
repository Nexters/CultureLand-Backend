package org.nexters.cultureland.api.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UserDto {
    private Long userId;
    private String userName;

    public UserDto(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
