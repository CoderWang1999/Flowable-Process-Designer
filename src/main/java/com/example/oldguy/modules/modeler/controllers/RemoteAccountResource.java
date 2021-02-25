package com.example.oldguy.modules.modeler.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.ui.common.model.RemoteUser;
import org.flowable.ui.common.model.UserRepresentation;
import org.flowable.ui.common.security.FlowableAppUser;
import org.flowable.ui.common.security.SecurityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: RemoteAccountResource
 * @Author: ren
 * @Description:
 * @CreateTIme: 2020/1/25 0025 下午 10:37
 **/
@Api(tags = "流程设计器用户权限配置")
@RestController
public class RemoteAccountResource {

    @ApiOperation("重新流程设计器中 获取用户信息")
    @GetMapping("app/rest/account")
    public UserRepresentation getAccount() {

        FlowableAppUser appUser = SecurityUtils.getCurrentFlowableAppUser();
        UserRepresentation userRepresentation = new UserRepresentation(appUser.getUserObject());
        if (appUser.getUserObject() instanceof RemoteUser) {
            RemoteUser temp = (RemoteUser) appUser.getUserObject();
            userRepresentation.setPrivileges(temp.getPrivileges());
        }
        return userRepresentation;
    }


}
