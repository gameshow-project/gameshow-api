package com.gameshow.api.user;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserSpecificationBuilder {

    private final List<SearchCriteriaUser> params;

    public UserSpecificationBuilder() {
        params = new ArrayList<SearchCriteriaUser>();
    }

    public UserSpecificationBuilder with(String key, String operation, String value) {
        params.add(new SearchCriteriaUser(key, operation, value));
        return this;
    }

    public Specification<User> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(UserSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }

}
