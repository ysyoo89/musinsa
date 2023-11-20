package com.project.musinsa.service;

import com.project.musinsa.api.response.BestItemResponse;
import com.project.musinsa.api.response.model.BestItemModel;
import com.project.musinsa.core.code.CategoryCode;
import com.project.musinsa.entity.CodiEntity;
import com.project.musinsa.model.convertor.CodiConvertor;
import com.project.musinsa.model.dto.CodiModel;
import com.project.musinsa.repository.CodiRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CodiServiceTest {
    @Spy
    private CodiConvertor codiConvertor = Mappers.getMapper(CodiConvertor.class);
    @Mock
    private CodiRepository codiRepository;
    @Spy
    @InjectMocks
    private CodiService codiService;

    private List<CodiEntity> codiEntities = new ArrayList<>();

    @BeforeEach
    private void init() {
        codiEntities.add(new CodiEntity("A", 11200L, 5500L, 4200L, 9000L, 2000L, 1700L, 1800L, 2300L));
        codiEntities.add(new CodiEntity("B", 10500L, 5900L, 3800L, 9100L, 2100L, 2000L, 2000L, 2200L));
        codiEntities.add(new CodiEntity("C", 10000L, 6200L, 3300L, 9200L, 2200L, 1900L, 2200L, 2100L));
        codiEntities.add(new CodiEntity("D", 10100L, 5100L, 3000L, 9500L, 2500L, 1500L, 2400L, 2000L));
        codiEntities.add(new CodiEntity("E", 10700L, 5000L, 3800L, 9900L, 2300L, 1800L, 2100L, 2100L));
        codiEntities.add(new CodiEntity("F", 11200L, 7200L, 4000L, 9300L, 2100L, 1600L, 2300L, 1900L));
        codiEntities.add(new CodiEntity("G", 10500L, 5800L, 3900L, 9000L, 2200L, 1700L, 2100L, 2000L));
        codiEntities.add(new CodiEntity("H", 10800L, 6300L, 3100L, 9700L, 2100L, 1600L, 2100L, 2000L));
        codiEntities.add(new CodiEntity("I", 11400L, 6700L, 3200L, 9500L, 2400L, 1700L, 1700L, 2400L));
    }

    @Test
    public void getBestItems() {
        List<BestItemModel> list = new ArrayList<>();
        list.add(new BestItemModel("C", "10,000", 10000L, CategoryCode.TOP));
        list.add(new BestItemModel("E", "5,000", 5000L, CategoryCode.OUTER));
        list.add(new BestItemModel("D", "3,000", 3000L, CategoryCode.PANTS));
        list.add(new BestItemModel("G", "9,000", 9000L, CategoryCode.SNEAKERS));
        list.add(new BestItemModel("A", "2,000", 2000L, CategoryCode.BAG));
        list.add(new BestItemModel("D", "1,500", 1500L, CategoryCode.HAT));
        list.add(new BestItemModel("I", "1,700", 1700L, CategoryCode.SOCK));
        list.add(new BestItemModel("F", "1,900", 1900L, CategoryCode.ACCESSORIES));
        BestItemResponse mockResponse = new BestItemResponse(list);
        doReturn(codiEntities).when(codiRepository).findAll();
        BestItemResponse resultResponse = codiService.getBestItems();

        assertThat(resultResponse.getTotalPrice()).isEqualTo(mockResponse.getTotalPrice());
    }

    @Test
    void getLowest() {
    }

    @Test
    void getCategoryItems() {
    }

    @Test
    void createAndModifyCodi() {
    }

    @Test
    void removeCodi() {
    }
}