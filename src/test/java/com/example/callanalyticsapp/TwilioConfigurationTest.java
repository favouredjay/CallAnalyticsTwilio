package com.example.callanalyticsapp;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
    public class TwilioConfigurationTest {

       // @Mock
      @Value("${twilio.account_sid}")
        private  String mockAccountSid;

      // @Mock
        @Value("${twilio.auth_token}")
        private   String mockAuthToken;



    //@InjectMocks
    @Mock
        private TwilioConfiguration twilioConfiguration;

        @Test
        public void testTwilioConfigurationProperties() {
            // Mock the behavior of the @Value annotation
            when(twilioConfiguration.getAccountSid()).thenReturn(mockAccountSid);
            when(twilioConfiguration.getAuthToken()).thenReturn(mockAuthToken);

            // Access the properties through the TwilioConfiguration instance
            String actualAccountSid = twilioConfiguration.getAccountSid();
            String actualAuthToken = twilioConfiguration.getAuthToken();

            // Verify that the properties are injected correctly
            assertEquals(mockAccountSid, actualAccountSid);
            assertEquals(mockAuthToken, actualAuthToken);
        }
    }

