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
    private String status;
    private String message;

    @Builder
    public ResponseMessage(String error, String status, String message) {
        this.timestamp = CurrentTime.getCurrentTimeStamp();
        this.error = error;
        this.status = status;
        this.message = message;
    }

    public static ResponseMessage getOkResponseMessage(){
        return ResponseMessage.builder().status("200").message("SUCCESS").build();
    }
}
