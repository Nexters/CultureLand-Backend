package org.nexters.cultureland.api.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter @NoArgsConstructor
public class UserDto {
    private Long userId;
    private String userName;
    private LocalDateTime createdBy;
    public UserDto(Long userId, String userName, LocalDateTime localDateTime) {
        this.userId = userId;
        this.userName = userName;
        this.createdBy = localDateTime;
    }
}
