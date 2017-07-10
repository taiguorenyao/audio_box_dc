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

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		super.onAuthenticationFailure(request, response, exception);
	}

	@Override
	public void setDefaultFailureUrl(String defaultFailureUrl) {
		super.setDefaultFailureUrl(defaultFailureUrl);
	}

	@Override
	public void setUseForward(boolean forwardToDestination) {
		super.setUseForward(forwardToDestination);
	}

	@Override
	protected RedirectStrategy getRedirectStrategy() {
		return super.getRedirectStrategy();
	}

	@Override
	protected boolean isAllowSessionCreation() {
		return super.isAllowSessionCreation();
	}

	@Override
	protected boolean isUseForward() {
		return super.isUseForward();
	}

	@Override
	public void setAllowSessionCreation(boolean allowSessionCreation) {
		super.setAllowSessionCreation(allowSessionCreation);
	}

	@Override
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		super.setRedirectStrategy(redirectStrategy);
	}

}
