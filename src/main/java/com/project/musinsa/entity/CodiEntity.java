package com.project.musinsa.entity;

import com.sun.istack.NotNull;
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

    @NotNull
    @Column(name = "JACKET")
    private Long top;

    @NotNull
    @Column(name = "OTR")
    private Long outer;

    @NotNull
    @Column(name = "PNT")
    private Long pants;

    @NotNull
    @Column(name = "SNKR")
    private Long sneakers;

    @NotNull
    @Column(name = "BACKPACK")
    private Long bag;

    @NotNull
    @Column(name = "CAPHAT")
    private Long hat;

    @NotNull
    @Column(name = "SCK")
    private Long sock;

    @NotNull
    @Column(name = "ACC")
    private Long accessories;
}
