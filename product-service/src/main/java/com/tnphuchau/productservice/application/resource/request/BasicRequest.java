package com.tnphuchau.productservice.application.resource.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BasicRequest {
	private String requestId;
	private String requestTime;
	private String signature;
}
