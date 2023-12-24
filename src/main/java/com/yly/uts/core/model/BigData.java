package com.yly.uts.core.model;

import lombok.Data;

@Data
public class BigData {

    private String id;

    private String blockDataJson;

    private byte[] blockDataJsonGzip;

}
