package com.project.musinsa.api.controller;

import com.project.musinsa.service.CodiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/codi")
public class CodiController {

    private final CodiService codiService;
    @GetMapping("/category/best-items")
    public ResponseEntity<?> getBestItems() {
        return ResponseEntity.ok(codiService.getBestItems());
    }

    @GetMapping("/category/lowest")
    public ResponseEntity<?> getLowest() {
        return ResponseEntity.ok(codiService.getLowest());
    }
}
