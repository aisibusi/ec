package com.lh.ec.common.enums;

import org.springframework.http.HttpStatus;

public enum ExceptionEnum {
    INVALID_PARAM(HttpStatus.BAD_REQUEST.value(), "Error for the param"),
    SPEC_PARAM_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Cannot get the specification parameters"),
    INVENTORY_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Cannot get the inventory"),
    GOODS_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Cannot get the goods"),
    SKU_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Cannot get the SKU"),
    SPU_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Cannot get the SPU"),
    PRICE_CANNOT_BE_NULL(HttpStatus.BAD_REQUEST.value(),"PRICE_CANNOT_BE_NULL"),
    CATEGORY_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Cannot get the category"),
    BRAND_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Cannot get the brand"),
    FAIL_TO_SAVE_BRAND(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Cannot create the brand"),
    INVALID_FILE_FORMAT(HttpStatus.BAD_REQUEST.value(), "The type of the file is incorrect"),
    UPLOAD_IMAGE_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception for uploading the file"),
    FAIL_TO_UPDATE_BRAND(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to update the brand information"),
    SPEC_GROUP_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Cannot get the Specification Group"),
    SPEC_PARAMS_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "Cannot get the Specification Params"),
    FAIL_TO_SAVE_SPECPARAM(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Cannot save the specification params"),
    FAIL_TO_DELETE_SPECPARAM(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Cannot delete the specification params");
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
