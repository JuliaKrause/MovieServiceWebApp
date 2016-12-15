package at.technikumwien.resources;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Collection;
import java.util.stream.Collectors;

//import org.jboss.logging.Logger;

/**
 * Created by Julia on 05.12.2016.
 */
public class StudioFilterInterceptor {
    //private static final Logger LOGGER = Logger.getLogger(StudioFilterInterceptor.class);
	private static final int MAX_SIZE = 20;

    @AroundInvoke
    public Object filter(InvocationContext ic) throws Exception {
		//LOGGER.info("filter() called");
		//LOGGER.debugf("> method=%s", ic.getMethod().getName());
		
        //wir wollen immer nur die ersten drei Studio-Einträge
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