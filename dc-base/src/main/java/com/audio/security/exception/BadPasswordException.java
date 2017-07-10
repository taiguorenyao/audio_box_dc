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

public class BadPasswordException extends AuthenticationException {

	private static final long serialVersionUID = 5682792870301501337L;

	public BadPasswordException(String msg) {
		super(msg);
	}

}
