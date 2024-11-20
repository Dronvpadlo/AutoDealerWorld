package com.example.autodealerworld.entity;

import com.example.autodealerworld.entity.enums.Currency;
import com.example.autodealerworld.entity.enums.RegionCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RegionCode code;

    public Region(String name, RegionCode code) {
        this.name = name;
        this.code = code;
    }
}
