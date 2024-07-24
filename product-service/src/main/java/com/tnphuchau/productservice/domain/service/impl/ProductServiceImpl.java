package com.tnphuchau.productservice.domain.service.impl;

import com.tnphuchau.productservice.application.common.ResultCode;
import com.tnphuchau.productservice.application.resource.request.DetailProductRequest;
import com.tnphuchau.productservice.application.resource.request.ListProductRequest;
import com.tnphuchau.productservice.application.resource.response.BasicResponse;
import com.tnphuchau.productservice.application.resource.response.DetailProductResponse;
import com.tnphuchau.productservice.application.resource.response.ListProductResponse;
import com.tnphuchau.productservice.application.resource.response.ResultResponse;
import com.tnphuchau.productservice.application.resource.response.data.DetailProductResponseData;
import com.tnphuchau.productservice.application.resource.response.data.ListProductResponseData;
import com.tnphuchau.productservice.domain.service.ProductService;
import com.tnphuchau.productservice.infrastructure.database.ProductDao;
import com.tnphuchau.productservice.application.resource.request.data.ListProductRequestData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public ResultResponse<DetailProductResponse> getDetailProduct(DetailProductRequest request, String path) {
        String methodName = "getDetailProduct";
        BasicResponse basicResponse = new BasicResponse();
        DetailProductResponse detailProductResponse = new DetailProductResponse();
        String requestId = request.getRequestId();
        try {
            DetailProductResponseData data = productDao.detailProduct(request.getData());
            if (data == null) {
                basicResponse.setResponseCode(ResultCode.NOT_FOUND.getCode());
                basicResponse.setResponseMessage(ResultCode.NOT_FOUND.getMessage());
            } else {
                detailProductResponse.setData(data);
                basicResponse.setResponseCode(ResultCode.SUCCESS.getCode());
                basicResponse.setResponseMessage(ResultCode.SUCCESS.getMessage());
            }
            basicResponse.setResponseId(UUID.randomUUID().toString());
        } catch (Exception e) {
            basicResponse.setResponseCode(ResultCode.SYSTEM_ERROR.getCode());
            basicResponse.setResponseMessage(ResultCode.SYSTEM_ERROR.getMessage());
        }
        ResultResponse<DetailProductResponse> response = new ResultResponse<>();
        response.setResponse(basicResponse);
        response.setResult(detailProductResponse);
        return response;
    }

    @Override
    public ResultResponse<ListProductResponse> getListProduct(ListProductRequest request, String path) {
        String methodName = "getListProduct";
        BasicResponse basicResponse = new BasicResponse();
        ListProductResponse listProductResponse = new ListProductResponse();
        String requestId = request.getRequestId();

        try {
            ListProductRequestData requestData = request.getData();

            int totalProducts = productDao.totalListProduct(requestData);
            List<ListProductResponseData> data = productDao.listProduct(requestData, requestData.getPageNumber(), requestData.getPageSize());

            listProductResponse.setData(data);
            listProductResponse.setTotal(String.valueOf(totalProducts));
            listProductResponse.setPageNumber(String.valueOf(requestData.getPageNumber()));
            listProductResponse.setPageSize(String.valueOf(requestData.getPageSize()));

            if (data.isEmpty()) {
                basicResponse.setResponseCode(ResultCode.NOT_FOUND.getCode());
                basicResponse.setResponseMessage(ResultCode.NOT_FOUND.getMessage());
            } else if (Integer.parseInt(listProductResponse.getTotal()) < Integer.parseInt(String.valueOf(requestData.getPageSize()))) {
                basicResponse.setResponseCode(ResultCode.SUCCESS.getCode());
                basicResponse.setResponseMessage(ResultCode.SUCCESS.getMessage());
            } else {
                basicResponse.setResponseCode(ResultCode.SUCCESS.getCode());
                basicResponse.setResponseMessage(ResultCode.SUCCESS.getMessage());
            }

            basicResponse.setResponseId(UUID.randomUUID().toString());
        } catch (Exception e) {
            basicResponse.setResponseCode(ResultCode.SYSTEM_ERROR.getCode());
            basicResponse.setResponseMessage(ResultCode.SYSTEM_ERROR.getMessage());
        }

        ResultResponse<ListProductResponse> response = new ResultResponse<>();
        response.setResponse(basicResponse);
        response.setResult(listProductResponse);
        return response;
    }
}
