package com.tnphuchau.productservice.application.resource.response;

import com.tnphuchau.productservice.application.resource.response.data.DetailProductResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailProductResponse {
    private DetailProductResponseData data;
}