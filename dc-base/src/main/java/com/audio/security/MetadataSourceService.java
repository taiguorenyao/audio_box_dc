/*
  本类初始化加载所有资源需要的角色关系
  初始化完毕后，返回请求地址所需要的角色列表
 */
package com.audio.security;

import java.util.*;

import com.audio.core.entity.Permission;
import com.audio.core.entity.Role;
import com.audio.core.entity.RolePermission;
import com.audio.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;


public class MetadataSourceService implements FilterInvocationSecurityMetadataSource {

	private static final Logger logger = LoggerFactory.getLogger(MetadataSourceService.class);
	// 加载所有资源与权限的关系
	private static Map<String, Collection<ConfigAttribute>> map = null;

	private PathMatcher matcher = new AntPathMatcher();

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		FilterInvocation filterInvocation = (FilterInvocation) object;

		if(map == null || map.isEmpty()){
			loadAccessableSource();
		}

		if(map.isEmpty()){
			Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
			ConfigAttribute configAttribute = new SecurityConfig("ROLE_ANONYMOUS");
			configAttributes.add(configAttribute);
			return configAttributes;
		}
		// 请求访问的url
		String requestUrl = filterInvocation.getRequestUrl();
		Collection<ConfigAttribute> collection = map.get(requestUrl);
		if(CollectionUtils.isEmpty(collection)){
			Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
			ConfigAttribute configAttribute = new SecurityConfig("ROLE_ANONYMOUS");
			configAttributes.add(configAttribute);
			return configAttributes;
		}
		return collection;
	}

	/**
	 * @PostConstruct 是Java EE 5引入的注解， Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
	 * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作， 加载所有资源与权限的关系
	 */
	public void loadAccessableSource() {
		map = new HashMap<String, Collection<ConfigAttribute>>();
		logger.info("==============获取角色权限信息==============");
		Collection<Role> roleCollection = ConfigUtil.getConfig().roleCfg.getAllRoleCache();
		if(CollectionUtils.isEmpty(roleCollection)){
			logger.info("角色缓存信息为空");
		} else {
			List<RolePermission> rolePermissionsList = new ArrayList<RolePermission>();
			for (Role role : roleCollection) {
				rolePermissionsList.addAll(role.getPermissionIds());
			}

			for(RolePermission rolePermission:rolePermissionsList){
				Permission permission = ConfigUtil.getConfig().permissionCfg.getPermission(rolePermission.getPermissionId());
				Role role = ConfigUtil.getConfig().roleCfg.getRoleCacheByRoleId(rolePermission.getRoleId());

				if(permission==null || role == null){
					continue;
				}

				Collection<ConfigAttribute> collection = map.get(permission.getPermissionUrl());
				if(null == collection){
					collection = new ArrayList<ConfigAttribute>();
					collection.add(new SecurityConfig("ROLE_"+role.getRoleName()));
					if(UrlUtils.isValidRedirectUrl(permission.getPermissionUrl())) {
						map.put(permission.getPermissionUrl(), collection);
					} else {
						logger.info("url is not valid:"+permission.getPermissionUrl());
					}
				} else {
					collection.add(new SecurityConfig("ROLE_"+role.getRoleName()));
				}
			}
		}
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	public PathMatcher getMatcher() {
		return matcher;
	}

	public void setMatcher(PathMatcher matcher) {
		this.matcher = matcher;
	}
}
