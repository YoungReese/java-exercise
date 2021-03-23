package com.ly.string;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BuilderTests {

    public static void main(String[] args) {
        Entity entity = new Entity();

        // 注意：这里 build 会返回一个新的对象
        entity = entity.builder().first("li").second("yang").build();
        System.out.println(entity);

        System.out.println("".equals(entity.getThird()) ? "空" : entity.getThird());
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Entity {
        String first;
        String second;
        String third;
    }

}

