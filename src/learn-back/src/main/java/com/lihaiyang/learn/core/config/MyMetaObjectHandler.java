package com.lihaiyang.learn.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lihaiyang.learn.core.utils.UserUtils;
import com.lihaiyang.learn.entity.User;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    public MyMetaObjectHandler() {
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createDate = this.getFieldValByName("createDate", metaObject);
        if (Objects.isNull(createDate)) {
            this.setFieldValByName("createDate", new Date(), metaObject);
        }

        Object updateDate = this.getFieldValByName("updateDate", metaObject);
        if (Objects.isNull(updateDate)) {
            this.setFieldValByName("updateDate", new Date(), metaObject);
        }
        Object obj = this.getFieldValByName("createBy", metaObject);
        if (Objects.isNull(obj)) {
            try {
                User user = UserUtils.getUser();
                this.setFieldValByName("createBy", user.getId(), metaObject);
                this.setFieldValByName("updateBy", user.getId(), metaObject);
            } catch (Exception var7) {
                 System.out.println("没有取到用户");

            }
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateDate = this.getFieldValByName("updateDate", metaObject);
        if (Objects.isNull(updateDate)) {
            this.setFieldValByName("updateDate", new Date(), metaObject);
        }

        Object obj = this.getFieldValByName("updateBy", metaObject);
        if (Objects.isNull(obj)) {
            try {
                User user = UserUtils.getUser();
                this.setFieldValByName("updateBy", user.getId(), metaObject);
            } catch (Exception var6) {
                System.out.println("没有取到用户");
            }
        }

    }
}
