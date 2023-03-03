package com.clock.common.enums;

/**
 * @className: CategoryType
 * @description: the type of category
 * @author: CLOCK
 */
public enum CategoryType {
    /**
     * type of blog
     */
    BLOG(1),
    /**
     * type of note
     */
    NOTE(2),
    /**
     * type of book
     */
    BOOK(3);
    private Integer type;

    CategoryType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
