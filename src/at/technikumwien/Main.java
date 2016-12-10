package at.technikumwien;

import at.technikumwien.entity.Actor;
import at.technikumwien.resources.ActorResource;

import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Julia on 10.12.2016.
 */
public class Main {
    public static void main (String [] args ) {
        //stopping this now because I think it makes more sense to have a client to test this
        //then the question still is: how do we get the JSON input?

        ActorResource ar = new ActorResource();
        Calendar cal = Calendar.getInstance();
        cal.set(1949, 12, 04);
        Date bd = cal.getTime();
        System.out.println(bd);
        Actor actor = new Actor("John", "Doe", "MALE", bd);


        //Response response = ar.createActor(jsonInString);
        //System.out.println(response);
    }
}
