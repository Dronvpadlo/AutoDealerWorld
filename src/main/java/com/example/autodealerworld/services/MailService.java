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

    String manager = "doronyuk714@gmail.com";
    public void notifyManagerForInvalidCar(Car car) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(manager);
        message.setSubject("Car Listing Needs Review");
        message.setText("Car with User ID " + car.getOwner().getId() + " contains inappropriate content and was deactivated.");
        mailSender.send(message);
    }
    public void notifyManagerForBrandNotExist(String brandName){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(manager);
        message.setSubject("Brand is not exist");
        message.setText("Brand with name " + brandName + " is not exist");
        mailSender.send(message);
    }

}
