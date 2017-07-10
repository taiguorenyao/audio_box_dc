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
package com.audio.security.exception;

import org.springframework.security.core.AuthenticationException;

public class BadValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = -6851210738862924772L;

	public BadValidateCodeException(String msg) {
		super(msg);
	}

}
