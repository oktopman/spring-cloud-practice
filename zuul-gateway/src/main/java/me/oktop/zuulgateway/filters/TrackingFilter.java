package me.oktop.zuulgateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TrackingFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    @Autowired
    FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isCorrelationIdPresent() {
        return filterUtils.getCorrelationId() != null;
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Object run() {
        if (isCorrelationIdPresent()) {
            System.out.println("tmx-correlation-id found in tracking filter : " + filterUtils.getCorrelationId());
        } else {
            filterUtils.setCorrelationId(generateCorrelationId());
            System.out.println("tmx-correlation-id generate in tracking filter : " + filterUtils.getCorrelationId());
        }

        RequestContext ctx= RequestContext.getCurrentContext();
        System.out.println("Processing incoming request for : " + ctx.getRequest().getRequestURI());
        return null;
    }
}
