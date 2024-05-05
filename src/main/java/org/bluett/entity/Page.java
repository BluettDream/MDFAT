package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Page<T> {
    /**
     * 当前页码
     */
    private Integer current;
    /**
     * 每页数量
     */
    private Integer size;
    /**
     * 总记录数
     */
    private Integer total;
    /**
     * 偏移量
     */
    private Integer offset;
    /**
     * 数据
     */
    private List<T> dataList;

    public Page<T> setData(List<T> dataList) {
        this.dataList = dataList;
        this.size = dataList.size();
        return this;
    }

    public Page<T> setResult(List<T> dataList, Integer total) {
        this.dataList = dataList;
        this.size = dataList.size();
        this.total = total;
        return this;
    }

    public Page(Integer current, Integer size) {
        this.current = current;
        this.size = size;
        this.offset = current * size;
    }

    public Page(Integer current, Integer size, Integer total) {
        this.current = current;
        this.size = size;
        this.total = total;
        this.offset = current * size;
    }

    private Page() {
    }
}
