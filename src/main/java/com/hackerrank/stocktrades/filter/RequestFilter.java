package com.hackerrank.stocktrades.filter;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hackerrank.stocktrades.util.Constants.ONLY_GET_TYPE_OF_REQUESTS_ARE_ALLOWED;

/**
 * This is a filter implementation that works on every request to apply certain common rules for
 * rejection/acceptance.
 */
@Component
public class RequestFilter implements Filter {


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if(checkSupportedMethod(req, res)){
            chain.doFilter(request, response);
        }
    }
    /**
     * Checks and returns whether a certain HTTP method is allowed to be processed or not.
     * @param request   HttpServletRequest  incoming request.
     * @param response  HttpServletResponse response to send.
     * @return  Boolean True if the method is allowed, false otherwise.
     * @throws IOException
     */
    private boolean checkSupportedMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String theMethod = request.getMethod();

        if (HttpMethod.GET.matches(theMethod) || HttpMethod.POST.matches(theMethod)) {
            return true;
        }
        else {
            response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(ONLY_GET_TYPE_OF_REQUESTS_ARE_ALLOWED);
            return false;
        }
    }


}
