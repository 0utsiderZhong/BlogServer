package com.clock.project.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: UserRole
 * @description: 用户和角色关联 sys_user_role
 * @author: Clock
 * 
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRole implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
