package org.nexters.cultureland.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nexters.cultureland.api.model.User;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String userName;
    private String eMail;
    private LocalDateTime createdBy;

    public UserDto(User user) {
        this.eMail = user.getEMail();
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.createdBy = user.getCreatedBy();
    }
}
