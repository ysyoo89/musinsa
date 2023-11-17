package com.project.musinsa.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CategoryCode {
    TOP("top", "상의"),
    OUTER("outer", "아우터"),
    PANTS("pants","바지"),
    SNEAKERS("sneakers", "스니커즈"),
    BAG("bag","가방"),
    HAT("hat","모자"),
    SOCK("sock","양말"),
    ACCESSORIES("accessories","액세서리");

    private final String category;
    private final String name;

    public static CategoryCode fromCode(String category) {
        return Arrays.stream(CategoryCode.values())
                .filter(m -> m.getCategory().equals(category))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("카테고리에 %s가 존재하지 않습니다.", category)));
    }

    public static CategoryCode fromName(String categoryName) {
        return Arrays.stream(CategoryCode.values())
                .filter(m -> m.getName().equals(categoryName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("카테고리에 %s가 존재하지 않습니다.", categoryName)));
    }
}
