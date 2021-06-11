package filters;

import com.example.ex4.beans.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * this class intercepts all requests and displays statistics: request processing duration
 *
 * it also demonstrates how to inject a bean (you cannot use Spring @Autowired in a
 * HandlerInterceptor but you can receive the bean via the ctor - from the configuration class)
 */

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
        if (!mySessionBean.getTest()){
            System.out.print("in intercepter. not logged in -> redirecting.");
            response.sendRedirect("/");
            return false;
        }
//        System.out.print("-------- intercepter: "+ mySessionBean.getTest() +" url: " + request.getRequestURL() + " uri: " + request.getRequestURI());

        return true;
    }
}



//@Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
//                           Object handler, ModelAndView modelAndView) throws Exception {
//
//        System.out.print("-------- postHandle ---: ");
//        System.out.println("Request URL: " + request.getRequestURL());
//
//        // You can add attributes in the modelAndView
//        // and use that in the view page
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
//                                Object handler, Exception ex) throws Exception {
//        System.out.print("-------- afterCompletion ---: ");
//
//        long startTime = (Long) request.getAttribute("startTime");
//        long endTime = System.currentTimeMillis();
//        System.out.print("Request URL: " + request.getRequestURL());
//        System.out.println("; End Time: " + endTime);
//
//        System.out.println("Time Taken: " + (endTime - startTime));
//    }