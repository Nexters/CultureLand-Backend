package org.nexters.cultureland.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SignDto {
    private String token;
    private String userName;

    @Builder
    public SignDto(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }
}
