package com.project.musinsa.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.musinsa.api.request.CodiRequest;
import com.project.musinsa.entity.CodiEntity;
import com.project.musinsa.model.convertor.CodiConvertor;
import com.project.musinsa.repository.CodiRepository;
import com.project.musinsa.service.CodiService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class CodiControllerTest {

    @Autowired
    private CodiService codiService;

    @Autowired
    private CodiRepository codiRepository;

    @Autowired
    private CodiConvertor codiConvertor;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext ctx;

    ObjectMapper mapper = new ObjectMapper();

    private static final String BASE_URL = "/api/v1/codi";

    @BeforeEach
    public void init() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("getBestItems Test")
    void getBestItems() throws Exception {
        mvc.perform(get(BASE_URL + "/category/best-items"))
                .andExpect(status().isOk())
                .andExpect(content().string(stringBestItemsJson()));
    }

    private String stringBestItemsJson() {
        return "{\"categories\":[{\"categoryName\":\"상의\",\"brandName\":\"C\",\"priceNumber\":\"10,000\"," +
                "\"price\":10000},{\"categoryName\":\"아우터\",\"brandName\":\"E\",\"priceNumber\":\"5,000\",\"price\":5000}," +
                "{\"categoryName\":\"바지\",\"brandName\":\"D\",\"priceNumber\":\"3,000\",\"price\":3000}," +
                "{\"categoryName\":\"스니커즈\",\"brandName\":\"A\",\"priceNumber\":\"9,000\",\"price\":9000}," +
                "{\"categoryName\":\"가방\",\"brandName\":\"A\",\"priceNumber\":\"2,000\",\"price\":2000}," +
                "{\"categoryName\":\"모자\",\"brandName\":\"D\",\"priceNumber\":\"1,500\",\"price\":1500}," +
                "{\"categoryName\":\"양말\",\"brandName\":\"I\",\"priceNumber\":\"1,700\",\"price\":1700}," +
                "{\"categoryName\":\"액세서리\",\"brandName\":\"F\",\"priceNumber\":\"1,900\",\"price\":1900}]," +
                "\"totalPrice\":\"34,100\"}";
    }

    @Test
    @DisplayName("getLowest Test")
    void getLowest() throws Exception {
        mvc.perform(get(BASE_URL + "/category/lowest"))
                .andExpect(status().isOk())
                .andExpect(content().string(stringLowestJson()));
    }

    private String stringLowestJson() {
        return "{\"최저가\":{\"브랜드\":\"D\",\"카테고리\":[{\"카테고리\":\"상의\",\"가격\":\"10,100\"}," +
                "{\"카테고리\":\"아우터\",\"가격\":\"5,100\"},{\"카테고리\":\"바지\",\"가격\":\"3,000\"}," +
                "{\"카테고리\":\"스니커즈\",\"가격\":\"9,500\"},{\"카테고리\":\"가방\",\"가격\":\"2,500\"}," +
                "{\"카테고리\":\"모자\",\"가격\":\"1,500\"},{\"카테고리\":\"양말\",\"가격\":\"2,400\"}," +
                "{\"카테고리\":\"액세서리\",\"가격\":\"2,000\"}],\"총액\":\"36,100\"}}";
    }

    @Test
    @DisplayName("getCategoryItems Test")
    void getCategoryItems() throws Exception{
        mvc.perform(get(BASE_URL + "/category/상의"))
                .andExpect(status().isOk())
                .andExpect(content().string(stringCategoryItemsJson()));
    }

    private String stringCategoryItemsJson() {
        return "{\"카테고리\":\"상의\"," +
                "\"최저가\":[{\"브랜드\":\"C\",\"가격\":\"10,000\"}]," +
                "\"최고가\":[{\"브랜드\":\"I\",\"가격\":\"11,400\"}]}";
    }

    @Test
    @DisplayName("Codi Save Test")
    void createCodi() throws Exception {
        CodiRequest request = new CodiRequest("J", 400L, 700L, 200L, 500L, 400L, 700L, 700L, 400L);
        mvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL + "/create")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                );
        Optional<CodiEntity> entity = codiRepository.findById("J");
        if (entity.isPresent()) {
            assertThat(entity.get().getBrand()).isEqualTo("J");
            assertThat(entity.get().getTop()).isEqualTo(400L);
        }
    }

    @Test
    @DisplayName("Codi Update Test")
    void modifyCodi() throws Exception{
        CodiRequest request = new CodiRequest("I", 400L, 700L, 3200L, 9500L, 2400L, 1700L, 1700L, 2400L);
        mvc.perform(MockMvcRequestBuilders
                .put(BASE_URL + "/modify")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        );
        Optional<CodiEntity> entity = codiRepository.findById("I");
        if (entity.isPresent()) {
            assertThat(entity.get().getOuter()).isEqualTo(700L);
            assertThat(entity.get().getTop()).isEqualTo(400L);
        }
    }

    @Test
    @DisplayName("Codi Delete Test")
    void removeCodi() throws Exception{
        CodiRequest request = new CodiRequest("I", 400L, 700L, 3200L, 9500L, 2400L, 1700L, 1700L, 2400L);
        mvc.perform(MockMvcRequestBuilders
                .delete(BASE_URL + "/remove")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        );
        Optional<CodiEntity> entity = codiRepository.findById("I");
        assertThat(entity.isPresent()).isEqualTo(false);
    }
}