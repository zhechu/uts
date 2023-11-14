package com.yly.uts.core.model;

import lombok.Data;

/**
 * 用户
 *
 * @author lingyuwang
 * @date 2023-11-14 4:25 上午
 * @since 0.0.1
 */
@Data
public class UtsUser {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

}
