package com.example.callanalyticsapp;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Call;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/calls")
public class CallController {


    @Autowired
    private CallService callService;


    @PostMapping("/makeCall")
    public ResponseEntity<String> initiateOutboundCall(@Valid @RequestBody CallRecordRequest callRecordRequest) {

        try {
            if (callRecordRequest == null) {
                log.error("Invalid CallRecord or toNumber is null");
                return ResponseEntity.badRequest().body("Invalid CallRecord or toNumber is null");
            }

            log.info("Initiating call to: " + callRecordRequest.getToNumber());
            callService.handleCalls(callRecordRequest);

            return ResponseEntity.ok("Call initiated to " + callRecordRequest.getToNumber());
        } catch (Exception e) {
            log.error("Failed to initiate call.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to initiate call.");
        }
    }

//    @GetMapping("/getCallDetails/{callSid}")
//    public ResponseEntity<CallRecord> getCallDetails(@PathVariable String callSid) {
//        try {
//            CallRecord callDetails = callService.getCallDetails(callSid);
//            return ResponseEntity.ok(callDetails);
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(null);
//        }
//    }

    @GetMapping("/getCallDetails/{callSid}")

    public Call getCallDetails(@PathVariable String callSid) {
        try {
            return Call.fetcher(callSid).fetch();


        } catch (ApiException e) {
            System.err.println("Twilio API exception: " + e.getMessage());
            throw new RuntimeException("Failed to get call details.", e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred.", e);
        }


    }

}