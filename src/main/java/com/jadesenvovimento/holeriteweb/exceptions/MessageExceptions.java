package com.jadesenvovimento.holeriteweb.exceptions;

import java.io.Serializable;
import java.util.Date;

public class MessageExceptions implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Date timeStamp;
    private Integer Status;
    private String message;

    public MessageExceptions(Date timeStamp, int status, String message) {
        super();
        this.timeStamp = timeStamp;
        Status = status;
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
