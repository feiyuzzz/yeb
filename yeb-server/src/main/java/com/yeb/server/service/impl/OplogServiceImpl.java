package com.yeb.server.service.impl;

import com.yeb.server.pojo.Oplog;
import com.yeb.server.mapper.OplogMapper;
import com.yeb.server.service.IOplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
