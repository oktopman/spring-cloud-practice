package me.oktop.clientservice.hystrix;

import me.oktop.clientservice.utils.UserContext;
import me.oktop.clientservice.utils.UserContextHolder;
import sun.print.PSPrinterJob;

import java.util.concurrent.Callable;

public class DelegatingUserContextCallable<V> implements Callable<V> {
    private final Callable<V> delegate; // @HystrixCommand 어노테이션에 메서드에 대한 프로퍼티
    private UserContext originalUserContext;

    public DelegatingUserContextCallable(Callable<V> delegate, UserContext originalUserContext) {
        this.delegate = delegate;
        this.originalUserContext = originalUserContext;
    }

    @Override // @HystrixCommand 어노테이션이 메서드를 보호하기 전에 호출하는 함수
    public V call() throws Exception {
        UserContextHolder.setContext(originalUserContext);
        try {
            return delegate.call(); // context가 설정되면 히스트릭스가 보호하는 메서드의 call() 메서드를 호출한다
        } finally {
            this.originalUserContext = null;
        }
    }

    public static <V> Callable<V> create(Callable<V> delegate, UserContext userContext) {
        return new DelegatingUserContextCallable<V>(delegate, userContext);
    }
}
