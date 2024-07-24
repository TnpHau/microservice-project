package com.tnphuchau.productservice.application.resource.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailProductResponseData {
    private String productId;
    private String productName;
    private String description;
    private BigDecimal price;
}
