package com.example.callanalyticsapp;

import com.twilio.exception.ApiException;

import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Service
public class CallService {

    @Autowired
    private TwilioConfiguration twilioConfiguration;

//    public void handleCalls(CallRecordRequest callRecordRequest) {
//        try {
//
//            if (isPhoneNumberValid(callRecordRequest.getToNumber())) {
//                PhoneNumber to = new PhoneNumber(callRecordRequest.getToNumber());
//                PhoneNumber from = new PhoneNumber(twilioConfiguration.getFromNumber());
//                Call call = Call.creator(to, from,  new com.twilio.type.Twiml("<Response><Say>Ahoy, World!</Say></Response>"))
//                        .create();
//                String callSid = call.getSid();
//                System.out.println("call sid =" + callSid);
//            }
//            else {
//                throw new IllegalArgumentException(
//                        "Phone number [" + callRecordRequest.getToNumber() + "] is not a valid number"
//                );
//            }
//
//
//        } catch (ApiException e) {
//            System.err.println("Twilio API exception: " + e.getMessage());
//            throw new RuntimeException("Failed to initiate call.", e);
//        } catch (Exception e) {
//            throw new RuntimeException("An unexpected error occurred.", e);
//        }
//        }


    public void handleCalls(CallRecordRequest callRecordRequest) {
        try {


            if (isPhoneNumberValid(callRecordRequest.getToNumber())) {
                PhoneNumber to = new PhoneNumber(callRecordRequest.getToNumber());
                PhoneNumber from = new PhoneNumber(twilioConfiguration.getFromNumber());
                Call call = Call.creator(to, from,  new     com.twilio.type.Twiml("<Response><Say>Ahoy, World!</Say></Response>"))
                        .create();
                String callSid = call.getSid();
                System.out.println("call sid =" + callSid);


            }
            else {
                throw new IllegalArgumentException(
                        "Phone number [" + callRecordRequest.getToNumber() + "] is not a valid number"
                );
            }


        } catch (ApiException e) {
            System.err.println("Twilio API exception: " + e.getMessage());
            throw new RuntimeException("Failed to initiate call.", e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred.", e);
        }
    }


    public boolean isPhoneNumberValid(String phoneNumber) {

        Pattern p = Pattern.compile("\\+\\d{10,}$");
        Matcher m = p.matcher(phoneNumber);
        return (m.find() && m.group().equals(phoneNumber));
    }


//    public Call getCallDetails(String callSid) {
//        try {
//            return Call.fetcher(callSid).fetch();
//
//        } catch (ApiException e) {
//            System.err.println("Twilio API exception: " + e.getMessage());
//            throw new RuntimeException("Failed to get call details.", e);
//        } catch (Exception e) {
//            throw new RuntimeException("An unexpected error occurred.", e);
//        }
//
//    }

    public CallRecord getCallDetails(String callSid){
        Call call = Call.fetcher(callSid).fetch();

        CallRecord callRecord = new CallRecord();
        callRecord.setFromNumber(call.getFrom());
        callRecord.setToNumber(call.getTo());
        callRecord.setDuration(call.getDuration() + "seconds");
        callRecord.setStatus(String.valueOf(call.getStatus()));

        return callRecord;
    }
}




