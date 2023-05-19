package edu.cczu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.cczu.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;


/**
 * 角色和菜单关联表(RoleMenu)数据库访问层
 *
 * @author 大只
 * @since 2022-11-06 18:45:38
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}


