package com.yeb.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeb.server.mapper.MenuMapper;
import com.yeb.server.pojo.Admin;
import com.yeb.server.pojo.Menu;
import com.yeb.server.service.IMenuService;
import com.yeb.server.utils.AdminUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Eddie
 * @since 2022-01-27
 */
@Slf4j(topic = "MenuServiceImpl")
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据用户id获取菜单
     *
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Admin admin = AdminUtils.getCurrentAdmin();
        //log.info("admin:{}",admin);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        List<Menu> menus = (List<Menu>) valueOperations.get("menu:" + admin.getId());
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.getMenusByAdminId(admin.getId());
            valueOperations.set("menu:"+admin.getId(),menus);
        }
        return menus;
    }

    /**
     * 根据角色权限获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * 查询所有的菜单
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

}
