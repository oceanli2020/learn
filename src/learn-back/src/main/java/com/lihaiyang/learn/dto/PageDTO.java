package com.lihaiyang.learn.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lihaiyang.learn.core.utils.StringUtils;
import java.util.Objects;
import lombok.Data;

@Data
public class PageDTO {

    private Long current;
    private Long size;
    private String sort;
    private String alias;
    private JSONObject query;

    public PageDTO() {
    }

    public <T> Page<T> getPage() {
        return new Page(this.current, this.size);
    }

    public String getSortSql() {
        StringBuilder sortSql = new StringBuilder();
        if (StringUtils.isNotEmpty(this.sort)) {
            String[] sortFields = this.sort.split(",");
            String[] var3 = sortFields;
            int var4 = sortFields.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String sortField = var3[var5];
                if (this.alias != null) {
                    sortSql.append(this.alias).append(".");
                }

                if (sortField.startsWith("-")) {
                    String tmp = StringUtils.toUnderScoreCase(sortField.substring(1));
                    sortSql.append(tmp).append(" desc,");
                } else {
                    sortSql.append(StringUtils.toUnderScoreCase(sortField)).append(" asc,");
                }
            }

            if (-1 != sortSql.indexOf(",", sortSql.length() - 1)) {
                sortSql.deleteCharAt(sortSql.length() - 1);
            }
        }

        return sortSql.toString();
    }

    public <T> T getQueryEntity(Class<T> classType) {
        if (Objects.isNull(this.query)) {
            try {
                return classType.newInstance();
            } catch (InstantiationException var3) {
                var3.printStackTrace();
            } catch (IllegalAccessException var4) {
                var4.printStackTrace();
            }
        }

        T entity = JSON.parseObject(this.query.toJSONString(), classType);
        return entity;
    }

    public <M> M getQueryField(String fieldName, Class<M> fieldType) {
        M fieldValue = null;
        if (this.query.containsKey(fieldName)) {
            fieldValue = this.query.getObject(fieldName, fieldType);
        }

        return fieldValue;
    }


    @Override
    public String toString() {
        return "PageDTO(current=" + this.getCurrent() + ", size=" + this.getSize() + ", sort=" + this.getSort() + ", alias=" + this.getAlias() + ", query=" + this.getQuery() + ")";
    }
}
