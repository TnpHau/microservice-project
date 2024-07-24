package com.tnphuchau.productservice.application.resource.response;

import com.tnphuchau.productservice.domain.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import static com.tnphuchau.productservice.application.common.Constant.PATTERN_DATE_TIME;

@Getter
@Setter
public class BasicResponse {
    private String responseId;

    private String responseTime = Utils.getCurrentDateTimeWithFormat(PATTERN_DATE_TIME);

    private String responseCode;

    private  String responseMessage;
}
