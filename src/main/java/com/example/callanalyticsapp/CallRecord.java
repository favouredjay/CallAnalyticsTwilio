package com.example.callanalyticsapp;
import lombok.Data;
@Data
public class CallRecord {
    private String toNumber;
    private String fromNumber;
    private String status;
    private String duration;
}




