package com.zzj.entity;

import com.zzj.utils.Constants;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Administrator on 2019/1/24.
 */
public class PageExample {

    private Integer pageSize = Constants.PAGE_SIZE;
    private Integer pageIndex = 0;
    private Integer pageCount;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public void clear() {
        pageIndex = 0;
    }

}
