package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public void notifyManagerForInvalidCar(Car car) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("doronyuk714@gmail.com");
        message.setSubject("Car Listing Needs Review");
        message.setText("Car with User ID " + car.getOwner().getId() + " contains inappropriate content and was deactivated.");
        mailSender.send(message);
    }

    public void notifyManagerForBrandNotExist(String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("doronyuk714@gmail.com");
        message.setSubject("Brand is not exist");
        message.setText(body);
        mailSender.send(message);
    }

}
