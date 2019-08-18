package org.nexters.cultureland.api.specification;

import org.nexters.cultureland.api.model.Culture;
import org.nexters.cultureland.api.model.CultureRawData;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class CultureSpec {
    public static Specification<CultureRawData> cultureName(final String category) {
        return new Specification<CultureRawData>() {
            @Override
            public Predicate toPredicate(Root<CultureRawData> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (StringUtils.isEmpty(category))
                    return null;

                Join<CultureRawData, Culture> c = root.join("culture", JoinType.INNER);
                return criteriaBuilder.equal(c.get("cultureName"), category);
            }
        };
    }
}
