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
package com.audio.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.audio.security.SecurityUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);
//	private Map<String, String> targetUrlMap;
	private String defaultTargetUrl;
	private RequestCache requestCache = new HttpSessionRequestCache();
	AntPathMatcher apm = new AntPathMatcher();

//	@Autowired
//	private UserServiceInterface userService;

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		String returnUrl = (String) request.getSession().getAttribute("returnUrl");
		if(UrlUtils.isValidRedirectUrl(returnUrl)){
			this.defaultTargetUrl = returnUrl;
		}

		super.setDefaultTargetUrl(defaultTargetUrl);
		SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
		if(userDetails!=null) {
			getRedirectStrategy().sendRedirect(request, response, this.defaultTargetUrl);
			return ;
		}

		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}
		String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}
		clearAuthenticationAttributes(request);
		// Use the DefaultSavedRequest URL
		String targetUrl = savedRequest.getRedirectUrl();
		logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);

//		logger.info();

		return;
		/*User user = null;
		try {
			user = (User) authentication.getPrincipal();
			user.setLastLoginIp(RequestUtil.getClientIP(request));
			userService.update(user);
		}
		catch (Exception e) {
			logger.debug("Update login user last login time and ip has error." + e.getMessage());
		}

		String requestUrl = request.getRequestURI();
		String matcherUrl = null;
		if (null != this.targetUrlMap) {
			for (Entry<String, String> url : this.targetUrlMap.entrySet()) {
				if (apm.match(url.getKey(), requestUrl)) {
					matcherUrl = url.getValue().toString();
					break;
				}
			}
		}
		if (null != matcherUrl) {
			this.defaultTargetUrl = matcherUrl;
		}
		super.setDefaultTargetUrl(this.defaultTargetUrl);

		if (null != user) {
			getRedirectStrategy().sendRedirect(request, response, this.defaultTargetUrl);
			return;
		}

		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest == null) {
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}
		String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}
		clearAuthenticationAttributes(request);
		// Use the DefaultSavedRequest URL
		String targetUrl = savedRequest.getRedirectUrl();
		logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);*/
	}

	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}

//	public void setTargetUrlMap(Map<String, String> targetUrlMap) {
//		this.targetUrlMap = targetUrlMap;
//	}

}
