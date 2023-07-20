package com.houseowner.edge;

import com.houseowner.edge.configuration.TwilioConfig;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EdgeApplication {

	private final TwilioConfig twilioConfig;

	public EdgeApplication(TwilioConfig twilioConfig)
	{
		this.twilioConfig = twilioConfig;
	}


	@PostConstruct
	public void initTwilio()
	{
		// configure Twilio with authentication
		Twilio.init(twilioConfig.getAccountSID(), twilioConfig.getAuthToken());
	}

	public static void main(String[] args)
	{
		// this is the main
		SpringApplication.run(EdgeApplication.class, args);
	}
}
