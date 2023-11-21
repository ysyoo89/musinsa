package com.project.musinsa.service;

import com.project.musinsa.api.request.CodiRequest;
import com.project.musinsa.api.response.BestItemResponse;
import com.project.musinsa.api.response.model.BestItemModel;
import com.project.musinsa.core.exception.code.ErrorCode;
import com.project.musinsa.core.exception.exception.CustomException;
import com.project.musinsa.core.util.CategoryUtil;
import com.project.musinsa.core.util.JsonUtil;
import com.project.musinsa.entity.CodiEntity;
import com.project.musinsa.model.convertor.CodiConvertor;
import com.project.musinsa.model.dto.CodiModel;
import com.project.musinsa.repository.CodiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        list.add(new BestItemModel().setAccessories(models));

        return new BestItemResponse(list);
    }

    public String getLowest() {
        List<CodiModel> models = codiConvertor.toDtoList(codiRepository.findAll());
        CodiModel model = models.stream()
                .min(Comparator.comparing(CodiModel::getTotalPrice))
                .orElseThrow(() -> new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
        Object lowestModel = codiConvertor.toLowest(model);
        return JsonUtil.lowestJson(model, lowestModel);
    }

    public String getCategoryItems(String categoryName) {
        List<CodiModel> models = codiConvertor.toDtoList(codiRepository.findAll());
        Comparator<CodiModel> comparator = CategoryUtil.getCategoryComparator(categoryName);
        CodiModel minModel = models.stream().min(comparator).orElseThrow(() -> new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
        CodiModel maxModel = models.stream().max(comparator).orElseThrow(() -> new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
        return JsonUtil.categoryItemJson(categoryName, minModel, maxModel);
    }

    @Transactional
    public void createAndModifyCodi(CodiRequest codiRequest) {
        try {
            CodiEntity entity = codiConvertor.createToEntity(codiRequest);
            codiRepository.save(entity);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.REQUEST_DATA_NULL_POINT);
        }
    }

    @Transactional
    public void removeCodi(CodiRequest codiRequest) {
        try {
            codiRepository.deleteById(codiRequest.getBrand());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.REQUEST_DATA_NULL_POINT);
        }
    }
}
