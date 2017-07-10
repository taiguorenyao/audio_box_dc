/**
 * 
 * This file is part of the KernelStudio package.
 * 
 * (c) 2014-2016 libertyspy <admin@kernelstudio.com>
 * 
 * For the full copyright and license information, please view the LICENSE file
 * that was distributed with this source code.
 * 
 * @author libertyspy < admin@kernelstudio.com >
 * @link http://www.kernelstudio.com
 * @version 0.1
 * @since 0.1
 */
package com.audio.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {

	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		//获取地址所需要的角色列表
		//获取用户拥有的角色列表
		//循环比较是否能访问该资源
		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			String needPermission = configAttribute.getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (null != needPermission && needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		throw new AccessDeniedException("权限不足！");
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
