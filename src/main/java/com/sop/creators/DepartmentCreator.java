package com.sop.creators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DepartmentCreator {
    private String name;
    private long hospitalId;
    private long availableBeds;
}
