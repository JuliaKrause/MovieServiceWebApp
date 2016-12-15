package at.technikumwien.resources;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * Created by Julia on 05.12.2016.
 */


@Provider
public class ResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        MultivaluedMap<String, Object> headers = response.getHeaders();

        //hiermit sagen wir, es ist uns egal, von welchem client wir aufgerufen werden
        //bringt aber nichts im Edge, muss anderen Browser nehmen
        headers.add("Access-Control-Allow-Origin", "*");

        //das brauchen wir erst für die security:
        headers.add("Access-Control-Allow-Headers", "Authorization");

        //wir hebeln aus, dass gecachet wird
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        headers.add("Cache-Control", cacheControl);
    }
}
