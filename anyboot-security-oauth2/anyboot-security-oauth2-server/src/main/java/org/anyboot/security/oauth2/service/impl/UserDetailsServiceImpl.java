package org.anyboot.security.oauth2.service.impl;
import org.anyline.entity.DataRow;
import org.anyline.entity.DataSet;
import org.anyline.service.impl.AnylineServiceImpl;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl extends AnylineServiceImpl implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DataRow tbUser = query("tb_user","username:"+username);
       log.warn(tbUser.toJSON());
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        if (tbUser != null) {
            // 获取用户授权
            DataSet premissions = querys("user:user_permission","user_id:"+ tbUser.getId());
            log.warn(premissions.toJSON());
            // 声明用户授权
            for(DataRow p:premissions){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(p.getString("enname"));
                grantedAuthorities.add(grantedAuthority);
            }
        }
        // 由框架完成认证工作
        return new User(tbUser.getString("username"), tbUser.getString("password"), grantedAuthorities);
    }
}