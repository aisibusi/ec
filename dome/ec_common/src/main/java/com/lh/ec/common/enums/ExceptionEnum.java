package com.lh.ec.common.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {
    PRICE_CANNOT_BE_NULL(HttpStatus.BAD_REQUEST.value(),"PRICE_CANNOT_BE_NULL"),
    CATEGORY_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Cannot get the category"),
    BRAND_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Cannot get the brand"),
    FAIL_TO_SAVE_BRAND(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Cannot create the brand"),
    INVALID_FILE_FORMAT(400, "The type of the file is incorrect"),
    UPLOAD_IMAGE_EXCEPTION(500, "Exception for uploading the file"),;
    private int code;
    private String msg;


    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
