package com.tnphuchau.productservice.application.resource.request.data;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListProductRequestData {
    private String categoryId;
    @NotNull
    private int pageNumber;
    @NotNull
    private int pageSize;
}


