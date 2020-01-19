package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.base.service.TreeServiceImpl;
import com.lihaiyang.learn.dao.MenuDao;
import com.lihaiyang.learn.entity.Menu;
import com.lihaiyang.learn.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends TreeServiceImpl<MenuDao, Menu> implements IMenuService {

    @Override
    public List<Menu> findByUserId(Menu menu) {
        return ((MenuDao)this.baseMapper).findByUserId(menu);
    }
}
