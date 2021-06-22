package filters;

import com.example.ex4.beans.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//an interceptor that checks always that the user is logged in. (accept by the url "/" or "login" obviously)
public class LoggingInterceptor implements HandlerInterceptor {

    private User mySessionBean;
    public LoggingInterceptor(User sessionBean) {
        this.mySessionBean = sessionBean;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(request.getRequestURI().equals("/login") || request.getRequestURI().equals("/") || request.getRequestURI().equals(""))
            return true;

        if (!mySessionBean.getLoggedIn()){
//            System.out.print("redirecting in interceptor. uri: " + request.getRequestURI());
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
