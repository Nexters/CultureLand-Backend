package org.nexters.cultureland.api.dto;

public enum Category {
    MUSICAL, CONCERT, PLAY, EXHIBITION, LIKE, ETC, NONE;

    public static Category getCategoryFrom(String input) {
        for (Category category : Category.values()) {
            if (category.equals(input.toUpperCase())) {
                return category;
            }
        }

        return NONE;
    }

    private boolean equals(String category) {
        return this.name().equals(category);
    }
}
