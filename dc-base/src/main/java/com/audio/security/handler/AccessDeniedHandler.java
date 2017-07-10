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

import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

	private String accessDeniedUrl;
	protected PathMatcher antPathMatcher = new AntPathMatcher();

	public AccessDeniedHandler() {
	}

	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException, ServletException {
		String uri = request.getRequestURI();
		response.sendRedirect(accessDeniedUrl);
		/*if (UserUtil.logined()) {
			request.getRequestDispatcher(accessDeniedUrl).forward(request, response);
		}
		else {
			response.sendRedirect(RequestUtil.getDomain(request) + accessDeniedUrl);
		}*/
	}

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
}