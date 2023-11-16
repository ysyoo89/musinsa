package com.project.musinsa.api.response;

import com.project.musinsa.api.response.model.BestItemModel;
import com.project.musinsa.core.util.NumberUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class BestItemResponse {
    private List<BestItemModel> categories;
    private String totalPrice;

    public BestItemResponse(List<BestItemModel> models) {
        this.categories = models;
        this.totalPrice = calculatorPrice(models);
    }
    private String calculatorPrice(List<BestItemModel> models) {
        return NumberUtil.numberComma(models.stream().mapToLong(BestItemModel::getPrice).sum());
    }
}
