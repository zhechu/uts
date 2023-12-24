package com.yly.uts.core.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class UtsTag {

    private String id;

    private String blockDataJson;

    private byte[] blockData;

}
