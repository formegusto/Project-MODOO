package com.modoo.wrk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class ModooFilter
 */
public class ModooFilter implements Filter {
	private List<String> unauth_allow_api = new ArrayList<String>(
			Arrays.asList( 
					"/MODOO", "/MODOO/", "/MODOO/403.jsp" , "/MODOO/account.jsp", "/MODOO/boardService.do", "/MODOO/boardDetailService.do"
				));
    /**
     * Default constructor. 
     */
    public ModooFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("[Filter] 로그인 검사");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		if (session != null) {
			if(session.getAttribute("user") != null) {
				System.out.println("로그인 된 사용자 입니다.");
				
				chain.doFilter(request, response);
			}
			else {
				System.out.println("로그인 안된 사용자 입니다.");
				String URI = req.getRequestURI();
				int pos = URI.lastIndexOf(".");
				String uriExt = URI.substring( pos + 1 );
				if(!unauth_allow_api.contains(URI) && !uriExt.equals("png") && !uriExt.equals("js") && !uriExt.equals("css")) {
					res.sendRedirect("/MODOO/403.jsp");
				} else {
					chain.doFilter(request, response);
				}
			}
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
