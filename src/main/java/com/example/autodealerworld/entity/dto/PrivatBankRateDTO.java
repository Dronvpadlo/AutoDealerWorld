package com.example.autodealerworld.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivatBankRateDTO {

    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}
