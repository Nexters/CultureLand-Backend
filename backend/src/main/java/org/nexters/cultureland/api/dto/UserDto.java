package org.nexters.cultureland.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nexters.cultureland.model.User;

import java.time.LocalDateTime;

@Getter @NoArgsConstructor
public class UserDto {
    private Long userId;
    private String userName;
    private LocalDateTime createdBy;

    public UserDto(User user){
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.createdBy = user.getCreatedBy();
    }
}
