package org.nexters.cultureland.common;

import java.sql.Timestamp;

public class CurrentTime {
    public static Timestamp getCurrentTimeStamp(){
        return new Timestamp(System.currentTimeMillis());
    }
}
