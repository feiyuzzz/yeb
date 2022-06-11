package com.yeb.server.service.impl;

import com.yeb.server.pojo.Role;
import com.yeb.server.mapper.RoleMapper;
import com.yeb.server.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Eddie
 * @since 2022-01-27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
