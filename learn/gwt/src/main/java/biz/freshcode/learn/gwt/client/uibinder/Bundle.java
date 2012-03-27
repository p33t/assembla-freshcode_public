package biz.freshcode.learn.gwt.client.uibinder;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Bundle extends ClientBundle {
    @Source("logo-med.jpg")
    ImageResource logo();
}
