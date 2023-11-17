package com.project.musinsa.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CodiRequest {
    private String brand;
    private Long top;
    private Long outer;
    private Long pants;
    private Long sneakers;
    private Long bag;
    private Long hat;
    private Long sock;
    private Long accessories;
}
