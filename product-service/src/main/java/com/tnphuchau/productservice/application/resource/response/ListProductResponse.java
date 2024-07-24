package com.tnphuchau.productservice.application.resource.response;

import com.tnphuchau.productservice.application.resource.response.data.ListProductResponseData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ListProductResponse {
    private List<ListProductResponseData> data;
    private String total;
    private String pageNumber;
    private String pageSize;
}

