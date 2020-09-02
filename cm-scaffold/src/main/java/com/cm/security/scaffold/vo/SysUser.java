package com.cm.security.scaffold.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description todo
 * @Author cm
 * @Date 2020/9/2 14:40
 */
@Data
public class SysUser {

  @ApiModelProperty("用户名")
  public String userName;

  @ApiModelProperty("性别")
  private String sex;

  @ApiModelProperty("年龄")
  private int Age;

  @ApiModelProperty("号码")
  private String phone;

}
