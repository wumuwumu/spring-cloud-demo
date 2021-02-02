package cn.overhill.security;

import cn.overhill.security.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {



    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setId(1L);
        user.setUsername("wumu");
        user.setPassword("123456");
       SecurityUser securityUser = new SecurityUser();
       securityUser.setCurrentUserInfo(user);
        // 返回UserDetails实现类
        return securityUser;
    }
}
