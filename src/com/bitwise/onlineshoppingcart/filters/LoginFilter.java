package com.bitwise.onlineshoppingcart.filters;

import com.bitwise.onlineshoppingcart.beans.LoginBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

/**
 * Created by arpitm on 8/24/2016.
 */
@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        HttpSession session=request.getSession();

        if(session.getAttribute("sessionId")!=null)
        {
            if(session.getAttribute("sessionId").equals(request.getSession().getId()))
            {
                chain.doFilter(req, resp);
            }
        }
        else
        {
//            request.getRequestDispatcher("/login").forward(request,response);
            response.sendRedirect("/login");
        }

    }

    public void init(FilterConfig config) throws ServletException {
    }
}
