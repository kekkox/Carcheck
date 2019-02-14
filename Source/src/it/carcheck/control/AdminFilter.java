package it.carcheck.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.carcheck.model.bean.AdminBean;

@WebFilter("/admin/internal/*")
public class AdminFilter implements Filter{

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//Needed in case of init of some parameters
	}
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession(false);
        
        if(session != null) {         
        	Object obj = session.getAttribute("user");
        	if(obj instanceof AdminBean) {
        		AdminBean admin = (AdminBean) obj;
        		if(admin.isFirstLogin())
        			response.sendRedirect(request.getContextPath() + "/admin/changePassword");
        		
        		arg2.doFilter(arg0, arg1);
        	}
        	else
        		response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
        }
        else
        	response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
	}

	@Override
	public void destroy() {
		//Needed in case of destroying resource on Servlet destroy
	}
}
