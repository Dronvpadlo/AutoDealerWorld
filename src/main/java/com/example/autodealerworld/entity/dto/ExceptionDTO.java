package com.example.autodealerworld.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionDTO {

    private int errorCode;

    private String field;

    private String message;
}
