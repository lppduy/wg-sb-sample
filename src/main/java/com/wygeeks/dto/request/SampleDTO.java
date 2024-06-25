package com.wygeeks.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class SampleDTO {
   private Integer id;
    private String name;
}
