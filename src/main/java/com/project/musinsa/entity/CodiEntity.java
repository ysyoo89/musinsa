package com.project.musinsa.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "TBL_CODI")
public class CodiEntity {
    @Id
    @Column(name = "CATEGORY")
    private String brand;

    @Column(name = "TOP")
    private Long top;

    @Column(name = "OUTERS")
    private Long outer;

    @Column(name = "PANTS")
    private Long pants;

    @Column(name = "SNEAKERS")
    private Long sneakers;

    @Column(name = "BAG")
    private Long bag;

    @Column(name = "HAT")
    private Long hat;

    @Column(name = "SOCKS")
    private Long socks;

    @Column(name = "ACCESSORIES")
    private Long accessories;
}
