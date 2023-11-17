package com.project.musinsa.api.controller;

import com.project.musinsa.api.request.CodiRequest;
import com.project.musinsa.service.CodiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<?> getCategoryItems(@PathVariable String categoryName) {
        return ResponseEntity.ok(codiService.getCategoryItems(categoryName));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCodi(@RequestBody CodiRequest codiRequest) {
        codiService.createCodi(codiRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyCodi(@RequestBody CodiRequest codiRequest) {
        codiService.modifyCodi(codiRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeCodi(@RequestBody CodiRequest codiRequest) {
        codiService.removeCodi(codiRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
