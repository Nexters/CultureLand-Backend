package org.nexters.cultureland.api.dto;

public enum Category {
    MUSICAL, CONCERT, PLAY, EXHIBITION, ETC, NONE;

    public static Category getCategoryFrom(String input) {
        for (Category category : Category.values()) {
            if (category.equals(input)) {
                return category;
            }
        }

        return NONE;
    }

    private boolean equals(String category) {
        return this.name().equals(category);
    }
}
