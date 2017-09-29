package biz.freshcode.learn.gwt2.mod2.shared.pkg.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("UnusedReturnValue")
@Path("/alt_dispatch")
@Produces(MediaType.APPLICATION_JSON)
public interface ActionDispatch {
    @POST
// Not possible?    <A extends Action<R>, R extends Result> R post(A action);
    StringResult post(TestAction action);
}
