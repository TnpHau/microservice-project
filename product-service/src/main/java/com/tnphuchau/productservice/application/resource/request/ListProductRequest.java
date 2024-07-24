package com.tnphuchau.productservice.application.resource.request;


import com.tnphuchau.productservice.application.resource.request.data.ListProductRequestData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListProductRequest extends BasicRequest {
    private ListProductRequestData data;
}