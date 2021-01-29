package com.gameshow.api.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteriaUser {

    private String key;
    private String operation;
    private String value;

}
