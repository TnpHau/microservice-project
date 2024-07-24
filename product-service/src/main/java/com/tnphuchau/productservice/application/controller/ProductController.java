package com.tnphuchau.productservice.application.controller;

import com.tnphuchau.productservice.application.common.Constant;
import com.tnphuchau.productservice.application.resource.request.BasicRequest;
import com.tnphuchau.productservice.application.resource.request.DetailProductRequest;
import com.tnphuchau.productservice.application.resource.request.ListProductRequest;
import com.tnphuchau.productservice.application.resource.response.BasicResponse;
import com.tnphuchau.productservice.application.resource.response.DetailProductResponse;
import com.tnphuchau.productservice.application.resource.response.ListProductResponse;
import com.tnphuchau.productservice.application.resource.response.ResultResponse;
import com.tnphuchau.productservice.domain.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.BiFunction;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private static final String CLASSNAME = ProductController.class.getSimpleName();

    private final ProductService productService;

    private <T, R extends BasicRequest> ResponseEntity<ResultResponse<T>> handleRequest(final String methodName, final R request, final String requestUrl, final BiFunction<R, String, ResultResponse<T>> serviceMethod) {
        ResponseEntity<ResultResponse<T>> responseEntity;
        ResultResponse<T> response = new ResultResponse<>();
        String requestId = request.getRequestId();
        try {
            response = serviceMethod.apply(request, requestUrl);
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            BasicResponse basicResponse = new BasicResponse();
            response.setResponse(basicResponse);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * Lấy dữ liệu detail từ Customer Journey
     * @param request DetailProductRequest
     * @return DetailProductResponse
     */
    @PostMapping(value = Constant.URL_GET_DETAIL)
    public ResponseEntity<ResultResponse<DetailProductResponse>> getDetailProduct(final @Valid @RequestBody DetailProductRequest request) {
        return handleRequest("getDetailProduct", request, Constant.URL_GET_DETAIL,
                productService::getDetailProduct);
    }

    /**
     * Lấy danh sách từ Customer Journey
     * @param request ListProductRequest
     * @return ListProductResponse
     */
    @PostMapping(value = Constant.URL_GET_LIST)
    public ResponseEntity<ResultResponse<ListProductResponse>> getListProduct(final @Valid @RequestBody ListProductRequest request) {
        return handleRequest("getListProduct", request, Constant.URL_GET_LIST,
                productService::getListProduct);
    }

}
