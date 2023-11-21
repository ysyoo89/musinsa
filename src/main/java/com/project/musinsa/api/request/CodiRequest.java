package com.project.musinsa.api.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodiRequest {
    @NotNull
    private String brand;
    @NotNull
    private Long top;
    @NotNull
    private Long outer;
    @NotNull
    private Long pants;
    @NotNull
    private Long sneakers;
    @NotNull
    private Long bag;
    @NotNull
    private Long hat;
    @NotNull
    private Long sock;
    @NotNull
    private Long accessories;
}
