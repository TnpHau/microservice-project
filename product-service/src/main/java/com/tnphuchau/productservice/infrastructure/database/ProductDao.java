package com.tnphuchau.productservice.infrastructure.database;

import com.tnphuchau.productservice.application.resource.request.data.DetailProductRequestData;
import com.tnphuchau.productservice.application.resource.request.data.ListProductRequestData;
import com.tnphuchau.productservice.application.resource.response.data.DetailProductResponseData;
import com.tnphuchau.productservice.application.resource.response.data.ListProductResponseData;

import java.util.List;

public interface ProductDao {
    DetailProductResponseData detailProduct(DetailProductRequestData requestData);
    List<ListProductResponseData> listProduct(ListProductRequestData requestData, int pageNumber, int pageSize);

    int totalListProduct(ListProductRequestData requestData);
}
