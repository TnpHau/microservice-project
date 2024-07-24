package com.tnphuchau.productservice.infrastructure.database.impl;


import com.tnphuchau.productservice.application.common.TableConstants;
import com.tnphuchau.productservice.infrastructure.database.ProductDao;
import com.tnphuchau.productservice.application.resource.request.data.DetailProductRequestData;
import com.tnphuchau.productservice.application.resource.request.data.ListProductRequestData;
import com.tnphuchau.productservice.application.resource.response.data.DetailProductResponseData;
import com.tnphuchau.productservice.application.resource.response.data.ListProductResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Value("${schema}")
    private String schema;

    @Override
    public DetailProductResponseData detailProduct (DetailProductRequestData requestData) {
        String methodName = "detailProduct";
        UUID productId = UUID.fromString(requestData.getProductId());
        System.out.println("Product ID: " + productId);
        String sql = String.format("SELECT * FROM %s.%s WHERE product_id = ?", schema, TableConstants.PRODUCT);
//        String sql = String.format("SELECT * FROM tnphuchau.product WHERE product_id = ?");
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{productId},
                    (rs, rowNum) -> new DetailProductResponseData(
                            rs.getString("product_id"),
                            rs.getString("product_name"),
                            rs.getString("description"),
                            rs.getBigDecimal("price")
                    ));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int totalListProduct(ListProductRequestData requestData) {
        UUID categoryId = UUID.fromString(requestData.getCategoryId());
        String sql = String.format("SELECT COUNT(*) FROM %s.%s WHERE category_id = ?", schema, TableConstants.PRODUCT);
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{categoryId}, Integer.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ListProductResponseData> listProduct(ListProductRequestData requestData, int page, int pageSize) {
        String methodName = "listProduct";
        UUID categoryId = UUID.fromString(requestData.getCategoryId());
        int offset = page * pageSize;
        String sql = String.format("SELECT * FROM %s.%s WHERE category_id = ? LIMIT ? OFFSET ?", schema, TableConstants.PRODUCT);

        try {
            return jdbcTemplate.query(sql, new Object[]{categoryId, pageSize, offset},
                    (rs, rowNum) -> new ListProductResponseData(
                            rs.getString("product_id"),
                            rs.getString("product_name"),
                            rs.getString("description"),
                            rs.getBigDecimal("price")
                    ));
        } catch (Exception e) {
            throw e;
        }
    }

}
