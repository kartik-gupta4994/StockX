package StockX.Kartik.Order_Service.Service;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//To attach JWT to service request
@Configuration
public class FeignClientConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // Extract JWT from current request
        String jwt = getCurrentJwt();
        if (jwt != null) {
            template.header("Authorization", jwt);
        }
    }

    private String getCurrentJwt() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            HttpServletRequest request = attrs.getRequest();
            return request.getHeader("Authorization");
        }
        return null;
    }
}

