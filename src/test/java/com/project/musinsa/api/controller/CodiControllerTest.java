package com.project.musinsa.api.controller;

import com.project.musinsa.service.CodiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CodiControllerTest {

    @InjectMocks
    private CodiService codiService;

    @Mock
    private MockMvc mockMvc;


    @Test
    void getBestItems() {
    }

    @Test
    void getLowest() {
    }

    @Test
    void getCategoryItems() {
    }

    @Test
    void createCodi() {
    }

    @Test
    void modifyCodi() {
    }

    @Test
    void removeCodi() {
    }
}