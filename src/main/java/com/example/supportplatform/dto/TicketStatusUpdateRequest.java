package com.example.supportplatform.dto;
import com.example.supportplatform.entity.TicketStatus;

public class TicketStatusUpdateRequest {

    private TicketStatus status;

    public TicketStatusUpdateRequest(){

    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
