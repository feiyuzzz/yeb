package com.yeb.server.mapper;

import com.yeb.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Eddie
 * @since 2022-01-27
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id获取菜单
     * @param id
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);


    /**
     * 根据角色权限获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询所有的菜单
     * @return
     */
    List<Menu> getAllMenus();
}
