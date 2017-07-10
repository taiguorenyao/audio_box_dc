/**
 * This file is part of the KernelStudio package.
 * <p>
 * (c) 2014-2016 libertyspy <admin@kernelstudio.com>
 * <p>
 * For the full copyright and license information, please view the LICENSE file
 * that was distributed with this source code.
 *
 * @author libertyspy < admin@kernelstudio.com >
 * @link http://www.kernelstudio.com
 * @version 0.1
 * @since 0.1
 */
package com.audio.security;

import com.audio.security.exception.BadAuthorityException;
import com.audio.security.exception.BadPasswordException;
import com.audio.security.exception.BadUsernameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    private String validateCodeTarget = "validateCode";
    private boolean useValidateCode = true;

    /**
     *
     * @param request
     * @param response
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        logger.info("login authentication");
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("utf-8 is unsupport encoding.", e);
        }

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);
        String validateCode = this.obtainValidateCode(request);
        String returnUrl = this.obtainReturnUrl(request);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authRequest);

        request.getSession(false).setAttribute("username",username);
        request.getSession(false).setAttribute("password",password);
        request.getSession(false).setAttribute("returnUrl",returnUrl);
        request.getSession(false).setAttribute("validateCode",validateCode);

        try {
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (BadUsernameException e) {
            throw new BadUsernameException("用户名错误.");
        } catch (BadPasswordException e) {
            throw new BadUsernameException("密码错误.");
        } catch (BadAuthorityException e) {
            throw new BadUsernameException("权限不足.");
        } catch (Exception ex) {
            throw new BadPasswordException("认证异常");
        }
        /*try {
			request.setCharacterEncoding("utf-8");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		LoginForm loginForm = new LoginForm();
		loginForm.setUsername(obtainUsername(request));
		loginForm.setPassword(obtainPassword(request));
		loginForm.setValidateCode(obtainValidateCode(request));

		request.getSession().setAttribute(SessionKey.LAST_ACTION_FORM, loginForm);

		if (null == loginForm.getUsername() || loginForm.getPassword().length() > usernameMaxLength) {
			throw new BadUsernameException("用户名/邮箱不能为空!");
		}

		if (null == loginForm.getPassword() || loginForm.getPassword().length() > passwordMaxLength) {
			throw new BadPasswordException("密码不能为空,且不能超过长度限制!");
		}

		if (useValidateCode && isInvalidValidateCode(request)) {
			throw new BadValidateCodeException("请输入正确的验证码.");
		}

		// 实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		try {
			return this.getAuthenticationManager().authenticate(authRequest);
		}
		catch (BadUsernameException e) {
			throw new BadUsernameException("用户名错误.");
		}
		catch (BadPasswordException e) {
			throw new BadUsernameException("密码错误.");
		}
		catch (BadAuthorityException e) {
			throw new BadUsernameException("权限不足.");
		}
		catch (Exception ex) {
			throw new BadPasswordException("用户名或密码错误.");
		}*/
    }

    /**
     *
     *
     * @param request
     *
     * @return boolean
     */
    protected boolean isInvalidValidateCode(HttpServletRequest request) {
        if (null != request && null != request.getSession()) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param request
     * @return
     */
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        Object obj = (String) request.getParameter(super.getUsernameParameter());
        return (null == obj || "".equals(obj)) ? null : obj.toString().trim();
    }

    /**
     *
     * @param request
     * @return
     */
    @Override
    protected String obtainPassword(HttpServletRequest request) {
        Object obj = (String) request.getParameter(super.getPasswordParameter());
        return (null == obj || "".equals(obj)) ? null : obj.toString();
    }

    /**
     *
     * @param request
     *
     * @return String
     */
    protected String obtainValidateCode(HttpServletRequest request) {
        Object obj = request.getParameter(getValidateCodeTarget());
        return (null == obj || "".equals(obj)) ? null : obj.toString();
    }

    /**
     *
     * @param request
     * @param authRequest
     */
    @Override
    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        super.setDetails(request, authRequest);
    }

    /**
     *
     * @param request
     *
     * @return String
     */
    protected String obtainReturnUrl(HttpServletRequest request) {
        Object obj = request.getParameter("returnUrl");
        return (null == obj || "".equals(obj)) ? null : obj.toString();
    }

    public String getValidateCodeTarget() {
        return validateCodeTarget;
    }

    public void setValidateCodeTarget(String validateCodeTarget) {
        this.validateCodeTarget = validateCodeTarget;
    }

    public boolean isUseValidateCode() {
        return useValidateCode;
    }

    public void setUseValidateCode(boolean useValidateCode) {
        this.useValidateCode = useValidateCode;
    }

}