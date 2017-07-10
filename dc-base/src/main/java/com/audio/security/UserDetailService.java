/*
 初始化登录用户权限及设置角色
 SecurityUserDetails
 */
package com.audio.security;

import com.audio.core.dao.user.UserDao;
import com.audio.core.entity.Role;
import com.audio.core.entity.User;
import com.audio.core.entity.UserRole;
import com.audio.util.ConfigUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDetailService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);
	@Autowired
	private UserDao userDao;
//	@Autowired
//	private UserServiceInterface userService;

	// 登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<User> userList = userDao.loadUserByLoginname(username);

		if(CollectionUtils.isEmpty(userList)){
			logger.debug("Query returned empty results for user '" + username + "'");
			throw new UsernameNotFoundException("User information is not found");
		}
		User user = userList.get(0);
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		List<UserRole> userRoleList = userDao.getUserRoleByUserId(user.getId());
		if(CollectionUtils.isEmpty(userRoleList)){
			authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		} else {
			for (UserRole userRole : userRoleList) {
				Role role = ConfigUtil.getConfig().roleCfg.getRoleCacheByRoleId(userRole.getRoleId());
				authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
			}
		}
		return new SecurityUserDetails(user,authorities);
		/*User user = null;
		try {
			if (ValidatorUtil.isEmail(username)) {
				user = userService.findOneByEmail(username);
			}
			else {
				user = userService.findOneByUsername(username);
			}
		}
		catch (Exception e) {
			throw new BadPasswordException("出现未知错误,请稍后重试.");
		}
		if (user == null) {
			// 用户名不存在:UsernameNotFoundException;
			// 密码错误:BadCredentialException;
			// 帐户被锁:LockedException;
			// 帐户未启动:DisabledException;
			// 密码过期:CredentialExpiredException
			throw new UsernameNotFoundException("用户名/邮箱或密码错误.");
		}
		return user;*/
	}

}
