package StudentManagementSystem.login.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hctrl
 * @date 2020/4/30 20:34
 */
public class LoginFilter implements Filter {

    private String redirectUrl;

    private List<String> passUrls = new ArrayList<>();


    @Override
    public void init(FilterConfig filterConfig){

        String ignoreURL = filterConfig.getInitParameter("passURL");
        System.out.println("ignoreURL   " + ignoreURL);
        redirectUrl = filterConfig.getInitParameter("redirectURL");
        System.out.println("redirectUrl  " + redirectUrl);

        //获取不拦截的url
        String[] ignoreURLArray = ignoreURL.split(",");
        for (String url : ignoreURLArray){
            System.out.println("url " + url);
            passUrls.add(url.trim());
        }


        System.out.println("init方法");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //请求的url
        String url = request.getRequestURI();
        System.out.println("url " + url);
        for (String urlStr : passUrls){

            if (url.indexOf(urlStr) > -1){
                filterChain.doFilter(request,response);
                return;
            }
        }

        HttpSession session = request.getSession();
        Object user = session.getAttribute("username");

        if (user != null){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect(redirectUrl);
        }

    }

    @Override
    public void destroy(){


    }

}
