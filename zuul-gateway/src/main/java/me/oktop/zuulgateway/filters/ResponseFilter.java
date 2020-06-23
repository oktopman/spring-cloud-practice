package me.oktop.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    @Autowired
    FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.POST_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        System.out.println("Adding the correlation id to the outboud headers." + filterUtils.getCorrelationId());
        context.getResponse().addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());

        System.out.println("Completing outing request for : " + context.getRequest().getRequestURI());
        return null;
    }
}
