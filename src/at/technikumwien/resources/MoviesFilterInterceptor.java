package at.technikumwien.resources;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Julia on 06.01.2017.
 */
public class MoviesFilterInterceptor {
    //private static final Logger LOGGER = Logger.getLogger(StudioFilterInterceptor.class);
    private static final int MAX_SIZE = 5;

    @AroundInvoke
    public Object filter(InvocationContext ic) throws Exception {
        //LOGGER.info("filter() called");
        //LOGGER.debugf("> method=%s", ic.getMethod().getName());

        Object result = ic.proceed();
        if(result instanceof Collection) {
            Collection<?> collection = (Collection<?>) result;
            //use streams with limit function
            return collection
                    .stream()
                    .limit(MAX_SIZE)
                    .collect(Collectors.toList());
        } else {
            return result;
        }
    }

}
