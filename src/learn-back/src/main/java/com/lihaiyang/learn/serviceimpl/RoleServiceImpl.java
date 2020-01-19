package com.lihaiyang.learn.serviceimpl;

import com.lihaiyang.learn.core.base.service.BaseServiceImpl;
import com.lihaiyang.learn.dao.MenuDao;
import com.lihaiyang.learn.dao.RoleDao;
import com.lihaiyang.learn.entity.Menu;
import com.lihaiyang.learn.entity.Role;
import com.lihaiyang.learn.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements IRoleService {

    @Override
    public List<Role> findByUserId(Role role){
        return ((RoleDao)this.baseMapper).findByUserId(role);
    }

}
