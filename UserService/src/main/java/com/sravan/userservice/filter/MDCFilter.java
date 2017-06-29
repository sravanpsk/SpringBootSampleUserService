package com.sravan.userservice.filter;

import java.io.IOException;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

import com.sravan.userservice.utils.JWTUtils;

public class MDCFilter implements Filter {

	private static final String USER = "USER";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("#############################################");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String header = httpRequest.getHeader("X-JWT-Assertion");
		if (header != null) {
			Map<String, Object> grants = JWTUtils.decode(header);
			MDC.put(USER, JWTUtils.getUser(grants));
		}

		try {
			chain.doFilter(request, response);
		} finally {
			MDC.remove(USER);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
