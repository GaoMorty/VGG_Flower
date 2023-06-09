package edu.cczu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.cczu.entity.Menu;
import edu.cczu.utils.Result;


/**
 * 菜单权限表(Menu)服务接口
 *
 * @author 大只
 * @since 2022-11-06 18:45:37
 */
public interface MenuService extends IService<Menu> {

    Result selectMenuPage(String keywords);

    Result selectRoleMenuIds(Long id);
}
