package com.project.musinsa.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.musinsa.api.response.BestItemResponse;
import com.project.musinsa.api.response.model.BestItemModel;
import com.project.musinsa.api.response.model.LowestModel;
import com.project.musinsa.core.code.CategoryCode;
import com.project.musinsa.core.util.NumberUtil;
import com.project.musinsa.model.convertor.CodiConvertor;
import com.project.musinsa.model.dto.CodiModel;
import com.project.musinsa.repository.CodiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CodiService {

    private final CodiRepository codiRepository;
    private final CodiConvertor codiConvertor;

    public BestItemResponse getBestItems() {
        List<CodiModel> models = codiConvertor.toDtoList(codiRepository.findAll());
        List<BestItemModel> list = new ArrayList<>();
        list.add(new BestItemModel().setTop(models));
        list.add(new BestItemModel().setOuter(models));
        list.add(new BestItemModel().setPants(models));
        list.add(new BestItemModel().setSneakers(models));
        list.add(new BestItemModel().setBag(models));
        list.add(new BestItemModel().setHat(models));
        list.add(new BestItemModel().setSock(models));
        list.add(new BestItemModel().setSneakers(models));
        list.add(new BestItemModel().setAccessories(models));

        return new BestItemResponse(list);
    }

    public String getLowest() {
        List<CodiModel> models = codiConvertor.toDtoList(codiRepository.findAll());
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getTotalPrice))
                .orElseThrow(NoSuchElementException::new);
        JsonObject jo = new JsonObject();
        JsonObject lowestObject = new JsonObject();
        lowestObject.addProperty("브랜드", model.getBrand());
        JsonArray array = new JsonArray();
        try {
            Object lowestModel = codiConvertor.toLowest(model);
            for (Field field : lowestModel.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                JsonObject categoryObject = new JsonObject();
                Object value = field.get(lowestModel);
                categoryObject.addProperty("카테고리", CategoryCode.fromCode(field.getName()).getName());
                categoryObject.addProperty("가격", NumberUtil.numberComma((Long) value));
                array.add(categoryObject);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        lowestObject.add("카테고리", array);
        lowestObject.addProperty("총액", NumberUtil.numberComma(model.getTotalPrice()));
        jo.add("최저가", lowestObject);
        return jo.toString();
    }
}
