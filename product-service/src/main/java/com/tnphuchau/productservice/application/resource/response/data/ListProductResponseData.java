package com.tnphuchau.productservice.application.resource.response.data;


import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListProductResponseData {
    private String productId;
    private String productName;
    private String description;
    private BigDecimal price;
}
