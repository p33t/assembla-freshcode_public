package biz.freshcode.learn.gwtlift.client;

import biz.freshcode.learn.gwtlift.shared.AnObject;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("experiment")
public interface ExperimentService extends RemoteService {
    String sendObject(AnObject obj);
}
