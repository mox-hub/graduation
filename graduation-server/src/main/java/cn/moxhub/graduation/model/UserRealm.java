package cn.moxhub.graduation.model;

import cn.moxhub.graduation.common.enums.HttpStatusEnum;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.user.User;
import cn.moxhub.graduation.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        ResponseDTO<User> responseDTO = userService.getUserInfoByUsername(username);
        if (responseDTO.getCode() == HttpStatusEnum.NOT_FOUND.getCode()) {
            throw new UnknownAccountException("用户不存在");
        }
        User user = responseDTO.getData();
        return new SimpleAuthenticationInfo(user, user.getUserPassword(), getName());
    }
}
