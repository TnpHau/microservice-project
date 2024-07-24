package com.tnphuchau.userservice.application.resource.response;

import static com.tnphuchau.userservice.application.common.Constant.PATTERN_DATE_TIME;

import com.tnphuchau.userservice.domain.utils.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicResponse {
    private String responseId;

    private String responseTime = Utils.getCurrentDateTimeWithFormat(PATTERN_DATE_TIME);

    private String responseCode;

    private  String responseMessage;
}
