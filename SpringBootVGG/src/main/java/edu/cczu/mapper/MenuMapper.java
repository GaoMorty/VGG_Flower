package edu.cczu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.cczu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 菜单权限表(Menu)数据库访问层
 *
 * @author 大只
 * @since 2022-11-06 18:45:35
 */

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {


    List<Long> selectRoleMenuList(Long id);

    List<String> selectUserPermission(Object id);
}


