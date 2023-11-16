package com.project.musinsa.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CodiModel {

    private String brand;
    private Long top;
    private Long outer;
    private Long pants;
    private Long sneakers;
    private Long bag;
    private Long hat;
    private Long sock;
    private Long accessories;

    public Long getTotalPrice() {
        return this.top + this.outer + this.pants + this.sneakers + this.bag + this.hat + this.sock + this.accessories;
    }
}
