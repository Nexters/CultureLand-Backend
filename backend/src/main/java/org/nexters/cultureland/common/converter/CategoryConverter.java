package org.nexters.cultureland.common.converter;

import org.nexters.cultureland.api.dto.Category;
import org.springframework.core.convert.converter.Converter;

public class CategoryConverter implements Converter<String, Category> {

    @Override
    public Category convert(final String category) {
        return Category.getCategoryFrom(category);
    }
}
