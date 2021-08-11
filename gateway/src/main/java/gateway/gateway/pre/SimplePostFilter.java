package gateway.gateway.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

public class SimplePostFilter extends ZuulFilter {


    private static Logger log = LoggerFactory.getLogger(SimplePostFilter.class);

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String requestAsString = request.getRequestURL().toString();
        boolean insert = requestAsString.contains("insert");
        System.out.println(insert);
        System.out.println(requestAsString);
        if (insert) {
            log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
           // throw new RuntimeException("Acces blocat la resursa");
        }
        return null;
    }



}
