package com.gameshow.api.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
@NoArgsConstructor
public class UserSpecification implements Specification<User> {

    private SearchCriteriaUser criteria;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.like(
                    builder.lower(
                            root.get(
                                    criteria.getKey()
                            )
                    ), "%" + criteria.getValue().toLowerCase() + "%"
            );
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.like(
                    builder.lower(
                            root.get(
                                    criteria.getKey()
                            )
                    ), "%" + criteria.getValue().toLowerCase() + "%"
            );
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        builder.lower(
                                root.get(
                                        criteria.getKey()
                                )
                        ), "%" + criteria.getValue().toLowerCase() + "%"
                );
            } else {
                return builder.equal(root.get(criteria.getKey().toLowerCase()), criteria.getValue().toLowerCase());
            }
        }
        return null;
    }

}
