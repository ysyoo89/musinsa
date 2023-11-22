package com.project.musinsa.api.response.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("serial")
public class LowestModel implements Serializable {
    private Long top;
    private Long outer;
    private Long pants;
    private Long sneakers;
    private Long bag;
    private Long hat;
    private Long socks;
    private Long accessories;
}
