package com.project.musinsa.api.response.model;

import com.project.musinsa.core.code.CategoryCode;
import com.project.musinsa.core.exception.code.ErrorCode;
import com.project.musinsa.core.exception.exception.CustomException;
import com.project.musinsa.core.util.NumberUtil;
import com.project.musinsa.model.dto.CodiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class BestItemModel {
    private String categoryName;
    private String brandName;
    private String priceNumber;
    private Long price;

    public BestItemModel(String brandName, String priceNumber, Long price, CategoryCode code) {
        this.categoryName = code.getName();
        this.brandName = brandName;
        this.priceNumber = priceNumber;
        this.price = price;
    }

    public BestItemModel setTop(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getTop))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_DATA));
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getTop()), model.getTop(), CategoryCode.TOP);
    }

    public BestItemModel setOuter(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getOuter))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_DATA));
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getOuter()), model.getOuter(), CategoryCode.OUTER);
    }

    public BestItemModel setPants(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getPants))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_DATA));
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getPants()), model.getPants(), CategoryCode.PANTS);
    }

    public BestItemModel setSneakers(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getSneakers))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_DATA));
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getSneakers()), model.getSneakers(), CategoryCode.SNEAKERS);
    }

    public BestItemModel setBag(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getBag))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_DATA));
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getBag()), model.getBag(), CategoryCode.BAG);
    }

    public BestItemModel setHat(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getHat))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_DATA));
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getHat()), model.getHat(), CategoryCode.HAT);
    }

    public BestItemModel setSocks(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getSocks))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_DATA));
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getSocks()), model.getSocks(), CategoryCode.SOCK);
    }

    public BestItemModel setAccessories(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getAccessories))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_DATA));
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getAccessories()), model.getAccessories(), CategoryCode.ACCESSORIES);
    }

}
