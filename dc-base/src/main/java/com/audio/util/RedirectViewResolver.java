package com.audio.util;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.RedirectView;

@Component
public class RedirectViewResolver implements ViewResolver {

	public static final String REDIRECT_PERMANENT_PREFIX = "redirectPermanent:";

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		if (viewName.startsWith(REDIRECT_PERMANENT_PREFIX)) {
			String redirectUrl = viewName.substring(REDIRECT_PERMANENT_PREFIX.length());
			RedirectView view = new RedirectView(redirectUrl);
			view.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
			return view;
		}
		return null;
	}

}