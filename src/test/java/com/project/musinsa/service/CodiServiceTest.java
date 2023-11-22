package com.project.musinsa.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.musinsa.api.request.CodiRequest;
import com.project.musinsa.api.response.BestItemResponse;
import com.project.musinsa.api.response.model.BestItemModel;
import com.project.musinsa.core.code.CategoryCode;
import com.project.musinsa.core.exception.code.ErrorCode;
import com.project.musinsa.core.exception.exception.CustomException;
import com.project.musinsa.entity.CodiEntity;
import com.project.musinsa.model.convertor.CodiConvertor;
import com.project.musinsa.repository.CodiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CodiServiceTest {
    @Spy
    private CodiConvertor codiConvertor = Mappers.getMapper(CodiConvertor.class);
    @Mock
    private CodiRepository codiRepository;
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
    @DisplayName("Success getBestItems")
    public void getBestItemsSuccess() {
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
    @DisplayName("Fail getBestItems")
    public void getBestItemsFail() {
        List<CodiEntity> emptyList = new ArrayList<>();
        doReturn(emptyList).when(codiRepository).findAll();

        CustomException e = assertThrows(CustomException.class, () -> codiService.getBestItems());
        assertThat(e.getErrorCode().getStatus()).isEqualTo(ErrorCode.NOT_DATA.getStatus());
    }

    @Test
    @DisplayName("Success getLowest")
    void getLowestSuccess() {
        doReturn(codiEntities).when(codiRepository).findAll();

        String mockResult = lowestJson();

        assertThat(codiService.getLowest()).isEqualTo(mockResult);
    }

    @Test
    @DisplayName("Fail getLowest")
    void getLowestFail() {
        List<CodiEntity> emptyList = new ArrayList<>();
        doReturn(emptyList).when(codiRepository).findAll();

        CustomException e = assertThrows(CustomException.class, () -> codiService.getLowest());
        assertThat(e.getErrorCode().getMessage()).isEqualTo(ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    private String lowestJson() {
        JsonObject jo = new JsonObject();
        JsonObject lowestObject = new JsonObject();
        lowestObject.addProperty("브랜드", "D");
        JsonArray array = new JsonArray();

        JsonObject categoryObject1 = new JsonObject();
        categoryObject1.addProperty("카테고리", "상의");
        categoryObject1.addProperty("가격", "10,100");
        array.add(categoryObject1);
        JsonObject categoryObject2 = new JsonObject();
        categoryObject2.addProperty("카테고리", "아우터");
        categoryObject2.addProperty("가격", "5,100");
        array.add(categoryObject2);
        JsonObject categoryObject3 = new JsonObject();
        categoryObject3.addProperty("카테고리", "바지");
        categoryObject3.addProperty("가격", "3,000");
        array.add(categoryObject3);
        JsonObject categoryObject4 = new JsonObject();
        categoryObject4.addProperty("카테고리", "스니커즈");
        categoryObject4.addProperty("가격", "9,500");
        array.add(categoryObject4);
        JsonObject categoryObject5 = new JsonObject();
        categoryObject5.addProperty("카테고리", "가방");
        categoryObject5.addProperty("가격", "2,500");
        array.add(categoryObject5);
        JsonObject categoryObject6 = new JsonObject();
        categoryObject6.addProperty("카테고리", "모자");
        categoryObject6.addProperty("가격", "1,500");
        array.add(categoryObject6);
        JsonObject categoryObject7 = new JsonObject();
        categoryObject7.addProperty("카테고리", "양말");
        categoryObject7.addProperty("가격", "2,400");
        array.add(categoryObject7);
        JsonObject categoryObject8 = new JsonObject();
        categoryObject8.addProperty("카테고리", "액세서리");
        categoryObject8.addProperty("가격", "2,000");
        array.add(categoryObject8);

        lowestObject.add("카테고리", array);
        lowestObject.addProperty("총액", "36,100");
        jo.add("최저가", lowestObject);

        return jo.toString();
    }

    @Test
    @DisplayName("Success getCategoryItems")
    void getCategoryItemsSuccess() {
        doReturn(codiEntities).when(codiRepository).findAll();

        String mockJson = mockCategoryItemJson();

        assertThat(codiService.getCategoryItems("상의")).isEqualTo(mockJson);
    }

    @Test
    @DisplayName("Fail getCategoryItems")
    void getCategoryItemsFail() {
        List<CodiEntity> emptyList = new ArrayList<>();
        doReturn(emptyList).when(codiRepository).findAll();

        CustomException e = assertThrows(CustomException.class, () -> codiService.getLowest());
        assertThat(e.getErrorCode().getMessage()).isEqualTo(ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    private String mockCategoryItemJson() {
        JsonObject jo = new JsonObject();
        jo.addProperty("카테고리", "상의");

        JsonArray minArray = new JsonArray();
        JsonObject minJo = new JsonObject();
        minJo.addProperty("브랜드", "C");
        minJo.addProperty("가격", "10,000");
        minArray.add(minJo);
        jo.add("최저가", minArray);

        JsonArray maxArray = new JsonArray();
        JsonObject maxJo = new JsonObject();
        maxJo.addProperty("브랜드", "I");
        maxJo.addProperty("가격", "11,400");
        maxArray.add(maxJo);

        jo.add("최고가", maxArray);
        return jo.toString();
    }

    @Test
    @DisplayName("Success Save")
    void createCodi() {
        final CodiEntity entity = new CodiEntity("J", 400L, 700L, 200L, 500L, 400L, 700L, 700L, 400L);
        final CodiRequest request = new CodiRequest("J", 400L, 700L, 200L, 500L, 400L, 700L, 700L, 400L);
        codiService.createCodi(request);

        verify(codiRepository).save(ArgumentMatchers.refEq(entity));
    }

    @Test
    @DisplayName("Fail save")
    void saveCodiFail(){
        CodiRequest emptyRequest = new CodiRequest();
        CustomException e = assertThrows(CustomException.class, () -> codiService.createCodi(emptyRequest));
        assertThat(e.getErrorCode().getMessage()).isEqualTo(ErrorCode.REQUEST_DATA_NULL_POINT.getMessage());
    }

    @Test
    @DisplayName("Success update")
    void modifyCodi() {
        final CodiEntity entity = new CodiEntity("I", 20000L, 6700L, 3200L, 9500L, 2400L, 1700L, 1700L, 2400L);
        final CodiRequest request = new CodiRequest("I", 20000L, 6700L, 3200L, 9500L, 2400L, 1700L, 1700L, 2400L);
        codiService.modifyCodi(request);

        verify(codiRepository).save(ArgumentMatchers.refEq(entity));
    }

    @Test
    @DisplayName("Fail update")
    void modifyCodiFail(){
        CodiRequest emptyRequest = new CodiRequest();
        CustomException e = assertThrows(CustomException.class, () -> codiService.modifyCodi(emptyRequest));
        assertThat(e.getErrorCode().getMessage()).isEqualTo(ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    @Test
    @DisplayName("Success Delete")
    void removeCodiSuccess() {
        final CodiEntity entity = new CodiEntity("I", 20000L, 6700L, 3200L, 9500L, 2400L, 1700L, 1700L, 2400L);
        final CodiRequest request = new CodiRequest("I", 20000L, 6700L, 3200L, 9500L, 2400L, 1700L, 1700L, 2400L);
        codiService.removeCodi(request);

        verify(codiRepository).deleteById(entity.getBrand());
    }

    @Test
    @DisplayName("Fail Delete")
    void removeCodiFail() {
        final CodiRequest emptyRequest = new CodiRequest();
        CustomException e = assertThrows(CustomException.class, () -> codiService.createCodi(emptyRequest));

        assertThat(e.getErrorCode().getMessage()).isEqualTo(ErrorCode.REQUEST_DATA_NULL_POINT.getMessage());
    }
}