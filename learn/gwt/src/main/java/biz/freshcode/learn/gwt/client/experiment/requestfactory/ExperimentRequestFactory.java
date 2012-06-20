package biz.freshcode.learn.gwt.client.experiment.requestfactory;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface ExperimentRequestFactory extends RequestFactory {

    LightbulbRequest lightbulbRequest();
    
}
