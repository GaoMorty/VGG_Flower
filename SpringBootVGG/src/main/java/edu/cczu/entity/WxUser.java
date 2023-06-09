package edu.cczu.entity;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (WxUser)表实体类
 *
 * @author 大只
 * @since 2022-11-12 16:27:16
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("wx_user")
public class WxUser {

    private Integer id;

    //微信开放id
    @TableId(value = "open_id", type = IdType.INPUT)
    private String openId;

    //用户随机昵称
    private String nickName;

    //账号状态（0正常 1停用）
    private String status;

    //创建时间
    private Date createTime;
}


