package com.niull.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author niull
 * @since 2020-11-30
 */
@Data
@TableName("t_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String account;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String email;

    private String password;

    private Integer sex;

    @Version
    private Integer version;

    @TableLogic(value = "0",delval = "1")
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
