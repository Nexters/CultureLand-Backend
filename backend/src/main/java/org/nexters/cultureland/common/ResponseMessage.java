package org.nexters.cultureland.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter @NoArgsConstructor
public class ResponseMessage {
    private Timestamp timestamp;
    private String error;
    private int code;
    private String message;

    @Builder
    public ResponseMessage(String error, int code, String message) {
        this.timestamp = CurrentTime.getCurrentTimeStamp();
        this.error = error;
        this.code = code;
        this.message = message;
    }

    public static ResponseMessage getOkResponseMessage(){
        return ResponseMessage.builder().code(200).message("Your request is succeed").build();
    }
}
