package com.audio.commons.code;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CaptchaCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 2697723978036851488L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/gif");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		try {
			
			Captcha captcha = new GifCaptcha(150,40,5);//   gif格式动画验证码
	        String vcode = captcha.out(response.getOutputStream());
	        request.getSession().removeAttribute("caonimadeyanzma");
	        request.getSession().setAttribute("caonimadeyanzma", vcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
