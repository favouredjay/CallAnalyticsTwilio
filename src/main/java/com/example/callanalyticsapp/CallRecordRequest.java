package com.example.callanalyticsapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CallRecordRequest {
    private String toNumber;
    public CallRecordRequest(@JsonProperty ("toNumber") String toNumber) {
        this.toNumber = toNumber;
    }
}
