package com.lihaiyang.learn.core.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

@Data
public class ResultList<T> {

    private Long totalElements;
    private List<T> content;

    public ResultList() {
    }

    public ResultList(IPage<T> page) {
        this.totalElements = page.getTotal();
        this.content = page.getRecords();
    }


    @Override
    public String toString() {
        return "ResultList(totalElements=" + this.getTotalElements() + ", content=" + this.getContent() + ")";
    }
}
