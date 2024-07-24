package com.tnphuchau.productservice.application.resource.request;

import com.tnphuchau.productservice.application.resource.request.data.DetailProductRequestData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DetailProductRequest extends BasicRequest {
    private DetailProductRequestData data;
}