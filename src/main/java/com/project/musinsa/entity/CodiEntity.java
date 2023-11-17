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
    @Column(name = "CATE")
    private String brand;

    @Column(name = "JACKET")
    private Long top;

    @Column(name = "OTR")
    private Long outer;

    @Column(name = "PNT")
    private Long pants;

    @Column(name = "SNKR")
    private Long sneakers;

    @Column(name = "BACKPACK")
    private Long bag;

    @Column(name = "CAPHAT")
    private Long hat;

    @Column(name = "SCK")
    private Long sock;

    @Column(name = "ACC")
    private Long accessories;
}
