package com.project.musinsa.core.util;

import com.project.musinsa.core.code.CategoryCode;
import com.project.musinsa.model.dto.CodiModel;

import java.util.Comparator;

public class CategoryUtil {

    public static Comparator<CodiModel> getCategoryComparator(String category) {
        CategoryCode code = CategoryCode.fromName(category);
        switch (code) {
            case TOP :
                return Comparator.comparing(CodiModel::getTop);
            case PANTS:
                return Comparator.comparing(CodiModel::getPants);
            case BAG:
                return Comparator.comparing(CodiModel::getBag);
            case OUTER:
                return Comparator.comparing(CodiModel::getOuter);
            case HAT:
                return Comparator.comparing(CodiModel::getHat);
            case SOCK:
                return Comparator.comparing(CodiModel::getSock);
            case SNEAKERS:
                return Comparator.comparing(CodiModel::getSneakers);
            case ACCESSORIES:
                return Comparator.comparing(CodiModel::getAccessories);
            default:
                return null;
        }
    }

    public static Long getCategory(CodiModel model, String category) {
        CategoryCode code = CategoryCode.fromName(category);
        switch (code) {
            case TOP :
                return model.getTop();
            case OUTER:
                return model.getOuter();
            case PANTS:
                return model.getPants();
            case SNEAKERS:
                return model.getSneakers();
            case BAG:
                return model.getBag();
            case HAT:
                return model.getHat();
            case SOCK:
                return model.getSock();
            case ACCESSORIES:
                return model.getAccessories();
            default:
                return 0L;
        }
    }
}
