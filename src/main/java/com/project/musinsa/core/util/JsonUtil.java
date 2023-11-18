package com.project.musinsa.core.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.musinsa.core.code.CategoryCode;
import com.project.musinsa.core.exception.code.ErrorCode;
import com.project.musinsa.core.exception.exception.CustomException;
import com.project.musinsa.model.dto.CodiModel;

import java.lang.reflect.Field;

public class JsonUtil {
    public static String lowestJson(CodiModel model, Object convertorModel) {
        JsonObject jo = new JsonObject();
        JsonObject lowestObject = new JsonObject();
        lowestObject.addProperty("브랜드", model.getBrand());
        JsonArray array = new JsonArray();
        try {
            for (Field field : convertorModel.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                JsonObject categoryObject = new JsonObject();
                Object value = field.get(convertorModel);
                categoryObject.addProperty("카테고리", CategoryCode.fromCode(field.getName()).getName());
                categoryObject.addProperty("가격", NumberUtil.numberComma((Long) value));
                array.add(categoryObject);
            }
        } catch (IllegalAccessException e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        lowestObject.add("카테고리", array);
        lowestObject.addProperty("총액", NumberUtil.numberComma(model.getTotalPrice()));
        jo.add("최저가", lowestObject);
        return jo.toString();
    }

    public static String categoryItemJson(String categoryName, CodiModel minModel, CodiModel maxModel) {
        JsonObject jo = new JsonObject();
        jo.addProperty("카테고리", categoryName);

        JsonArray minArray = new JsonArray();
        JsonObject minJo = new JsonObject();
        minJo.addProperty("브랜드", minModel.getBrand());
        minJo.addProperty("가격", NumberUtil.numberComma(CategoryUtil.getCategory(minModel, categoryName)));
        minArray.add(minJo);
        jo.add("최저가", minArray);

        JsonArray maxArray = new JsonArray();
        JsonObject maxJo = new JsonObject();
        maxJo.addProperty("브랜드", maxModel.getBrand());
        maxJo.addProperty("가격", NumberUtil.numberComma(CategoryUtil.getCategory(maxModel, categoryName)));
        maxArray.add(maxJo);

        jo.add("최고가", maxArray);

        return jo.toString();
    }
}
