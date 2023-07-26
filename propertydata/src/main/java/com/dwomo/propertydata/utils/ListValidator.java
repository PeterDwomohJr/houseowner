package com.dwomo.propertydata.utils;

import com.dwomo.propertydata.events.domain.Message;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ListValidator implements ConstraintValidator<NoPersonallyIdentifiableInformation, List<Message>> {

    @Override
    public void initialize(NoPersonallyIdentifiableInformation constraintAnnotation) {}

    /**
     */
    @Override
    public boolean isValid(List<Message> messages, ConstraintValidatorContext constraintValidatorContext) {

        if (messages == null) {
            return true;  // null list are considered valid
        }

        for (Message message : messages) {
            boolean messageIsNotNull = message != null;
            if (messageIsNotNull && containsPersonallyIdentifiableInformation(message)) {
                return false;  // list contains personally identifiable information
            }
        }

        return true;   // no personally identifiable information found in the message
    }


    private boolean containsPersonallyIdentifiableInformation(Message message) {

        // assert that the message is not null so that it don't result in a null pointer exception
        assert message != null;
        boolean messageValueIsNotNull = message.getMessage() != null;

        // define the regular expression pattern to match the personally identifiable information (pii)
        String pii = "^(?!.*(?:\\b\\d{10}\\b|(?i)at\\b|@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,4}\\b|\\b(?:facebook|twitter|instagram)\\.com\\b)).*$";

        // checks if the message matches any personally identifiable information
        return messageValueIsNotNull && !message.getMessage().matches(".*" + pii + ".*");
    }
}