package biz.freshcode.learn.gwt.mod1.client.experiment.requestfactory;

import biz.freshcode.learn.gwt.server.Lightbulb;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Lightbulb.class)
public interface LightbulbProxy extends EntityProxy {
    Boolean getIsOn();

    void setIsOn(Boolean b);

    String getId();

    void setId(String id);

    int getVersion();

    void setVersion(int v);
}
