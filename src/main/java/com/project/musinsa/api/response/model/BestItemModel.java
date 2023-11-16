package com.project.musinsa.api.response.model;

import com.project.musinsa.core.code.CategoryCode;
import com.project.musinsa.core.util.NumberUtil;
import com.project.musinsa.model.dto.CodiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

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
                .orElseThrow(NoSuchElementException::new);
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getTop()), model.getTop(), CategoryCode.TOP);
    }

    public BestItemModel setOuter(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getOuter))
                .orElseThrow(NoSuchElementException::new);
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getOuter()), model.getOuter(), CategoryCode.OUTER);
    }

    public BestItemModel setPants(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getPants))
                .orElseThrow(NoSuchElementException::new);
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getPants()), model.getPants(), CategoryCode.PANTS);
    }

    public BestItemModel setSneakers(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getSneakers))
                .orElseThrow(NoSuchElementException::new);
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getSneakers()), model.getSneakers(), CategoryCode.SNEAKERS);
    }

    public BestItemModel setBag(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getBag))
                .orElseThrow(NoSuchElementException::new);
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getBag()), model.getBag(), CategoryCode.BAG);
    }

    public BestItemModel setHat(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getHat))
                .orElseThrow(NoSuchElementException::new);
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getHat()), model.getHat(), CategoryCode.HAT);
    }

    public BestItemModel setSock(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getSock))
                .orElseThrow(NoSuchElementException::new);
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getSock()), model.getSock(), CategoryCode.SOCK);
    }

    public BestItemModel setAccessories(List<CodiModel> models) {
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getAccessories))
                .orElseThrow(NoSuchElementException::new);
        return new BestItemModel(model.getBrand(), NumberUtil.numberComma(model.getAccessories()), model.getAccessories(), CategoryCode.ACCESSORIES);
    }

}
