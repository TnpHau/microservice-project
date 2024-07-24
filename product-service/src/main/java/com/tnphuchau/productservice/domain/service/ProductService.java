package com.tnphuchau.productservice.domain.service;


import com.tnphuchau.productservice.application.resource.request.DetailProductRequest;
import com.tnphuchau.productservice.application.resource.request.ListProductRequest;
import com.tnphuchau.productservice.application.resource.response.DetailProductResponse;
import com.tnphuchau.productservice.application.resource.response.ListProductResponse;
import com.tnphuchau.productservice.application.resource.response.ResultResponse;

public interface ProductService {
    ResultResponse<DetailProductResponse> getDetailProduct(DetailProductRequest request, String path);
    ResultResponse<ListProductResponse> getListProduct(ListProductRequest request, String path);
}

