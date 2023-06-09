package edu.cczu.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.cczu.config.WxMappingJackson2HttpMessageConverter;
import edu.cczu.dto.WxUserLoginDto;
import edu.cczu.entity.WxUser;
import edu.cczu.error.SystemException;
import edu.cczu.mapper.WxUserMapper;
import edu.cczu.service.WxUserService;
import edu.cczu.utils.AppHttpCodeEnum;
import edu.cczu.utils.RandomUtils;
import edu.cczu.utils.Result;
import edu.cczu.utils.SysConstant;
import edu.cczu.vo.PageVo;
import edu.cczu.vo.WxLoginVo;
import edu.cczu.vo.WxUserLoginVo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Objects;

/**
 * (WxUser)表服务实现类
 *
 * @author 大只
 * @since 2022-11-12 16:27:16
 */
@Service("wxUserService")
@Data
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {

    @Value("${wx-config.appid}")
    private String appid;

    @Value("${wx-config.secret}")
    private String secret;

    @Value("${wx-config.url}")
    private String url;

    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Result wxUserLogin(WxUserLoginDto wxUserLoginDto) {

        String code = wxUserLoginDto.getCode();

        //拼接微信api请求地址，获取openID
        String wxUrl = String.format(url, appid, secret, code);

        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        String forObject = restTemplate.getForObject(wxUrl, String.class);
        //向微信小程序的服务器发起get请求
        WxLoginVo wxLoginVo = JSON.parseObject(forObject, WxLoginVo.class);
        if (Objects.isNull(wxLoginVo)) {
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }

        //得到openId
        String openId = wxLoginVo.getOpenId();

        //判断用户是否存在
        WxUser user = getOne(new QueryWrapper<WxUser>().lambda().eq(WxUser::getOpenId, openId));

        //自动注册
        if (Objects.isNull(user)) {
            //为空新增该用户
            WxUser wxUser = new WxUser();
            //生成随机昵称
            String random = RandomUtils.getRandom(4);
            String nickName = "微信用户" + random;
            wxUser.setNickName(nickName);
            wxUser.setOpenId(openId);
            wxUser.setCreateTime(new Date());

            if (save(wxUser)) {
                //然后返回token
                StpUtil.login(openId);
                String token = StpUtil.getTokenValue();
                return Result.okResult(new WxUserLoginVo(nickName,token));
            }
        }

        //登录
        if(user.getStatus().equals(SysConstant.USER_STATE_BAN)){
            //账号停用
            throw new SystemException(AppHttpCodeEnum.USER_BAN);
        }

        //登录并直接返回token
        StpUtil.login(openId);
        String token = StpUtil.getTokenValue();
        return Result.okResult(new WxUserLoginVo(user.getNickName(),token));
    }

    @Override
    public Result userList(Integer pageNum, Integer pageSize, String keywords) {
        LambdaQueryWrapper<WxUser> queryWrapper = new LambdaQueryWrapper<>();
        //模糊查询
        queryWrapper.like(Objects.nonNull(keywords), WxUser::getOpenId, keywords);

        //分页
        Page<WxUser> wxUserPage = new Page<>(pageNum, pageSize);
        page(wxUserPage, queryWrapper);
        PageVo pageVo = new PageVo(wxUserPage.getRecords(), wxUserPage.getTotal());
        return Result.okResult(pageVo);
    }
}
