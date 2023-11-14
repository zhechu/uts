package com.yly.uts.core.page;

import java.io.Serializable;

public class PageBase implements Serializable {

    private static final long serialVersionUID = -8542306234942287074L;

    public final static Integer DEFAULT_PAGE_INDEX = 1;
    public final static Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 页码，默认是 1（pageNumber为负数则自动转为1）
     */
    private Integer pageNumber = DEFAULT_PAGE_INDEX;
    /**
     * 每页条数，默认是 10（pageSize为负数则不分页）
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    public void setPageNumber(Integer pageNumber) {
        if (null == pageNumber) {
            this.pageNumber = DEFAULT_PAGE_INDEX;
        } else {
            this.pageNumber = pageNumber;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (null == pageSize) {
            this.pageSize = DEFAULT_PAGE_SIZE;

        } else {
            this.pageSize = pageSize;
        }
    }

    /**
     * 设置不分页
     *
     * @author lingyuwang
     * @date 2020-01-11 10:48
     * @since 1.0.4
     */
    public void setNoPage() {
        this.pageNumber = -1;
        this.pageSize = -1;
    }

    /**
     * 是否分页
     *
     * @return boolean
     * @author lingyuwang
     * @date 2020-01-11 12:31
     * @since 1.0.4
     */
    public boolean isNoPage() {
        return this.pageNumber != null && this.pageNumber == -1
                && this.pageSize != null && this.pageSize == -1;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

}
