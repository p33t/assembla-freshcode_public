package biz.freshcode.learn.gwt.client.experiment.requestfactory;

import biz.freshcode.learn.gwt.server.Lightbulb;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;

@Service(Lightbulb.class)
public interface LightbulbRequest extends RequestContext {
    // TODO: Service methods.  1 for each type (= 3)

    Request<List<LightbulbProxy>> findAll();
}
