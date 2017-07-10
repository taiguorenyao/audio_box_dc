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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationEntryPoint.class);
	private boolean forceHttps = false;
	private boolean useForward = false;
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public AuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//		super.commence(request, response, authException);
		String redirectUrl = null;
		String uri = request.getRequestURI();

		if (useForward) {

			if (forceHttps && "http".equals(request.getScheme())) {
				// First redirect the current request to HTTPS.
				// When that request is received, the forward to the login page will be
				// used.
				redirectUrl = buildHttpsRedirectUrlForRequest(request);
			}

			if (redirectUrl == null) {
				String loginForm = determineUrlToUseForThisRequest(request, response,
						authException);

				if (logger.isDebugEnabled()) {
					logger.debug("Server side forward to: " + loginForm);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(loginForm);

				dispatcher.forward(request, response);

				return;
			}
		}
		else {
			// redirect to login page. Use https if forceHttps true

			redirectUrl = buildRedirectUrlToLoginPage(request, response, authException);

		}

		redirectUrl = redirectUrl + "?uri="+ uri;

		redirectStrategy.sendRedirect(request, response, redirectUrl);
	}
}