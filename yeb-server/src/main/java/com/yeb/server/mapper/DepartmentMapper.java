package com.yeb.server.mapper;

import com.yeb.server.pojo.Department;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> getAllDepartments(Integer parentId);

    /**
     * 添加部门
     * @param department
     * @return
     */
    void addDep(Department department);

    /**
     * 删除部门
     * @param dep
     */
    void deleteDep(Department dep);
}
