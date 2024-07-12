package cn.evlight.gateway.center.types.result;

import java.util.Collections;
import java.util.List;

/**
 * @Description: 分页查询响应结果
 * @Author: evlight
 * @Date: 2024/7/8
 */
public class PageResult<T> {

    private Long total;
    private Long pages;
    private List<T> dataList;

    public PageResult(Long total, Long pages, List<T> dataList) {
        this.total = total;
        this.pages = pages;
        this.dataList = dataList;
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L, 0L, Collections.emptyList());
    }

    public static <T> PageResult<T> of(long total, long limit, List<T> dataList) {
        return new PageResult<>(total, (total + limit - 1) / total, dataList);
    }

}
