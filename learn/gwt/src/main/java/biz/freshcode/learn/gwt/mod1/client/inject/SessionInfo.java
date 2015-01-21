package biz.freshcode.learn.gwt.mod1.client.inject;

import com.gwtplatform.dispatch.rpc.shared.Result;

public interface SessionInfo {
    String getUserName();

    class Bean implements Result, SessionInfo {
        // various bits of session info that will be injected as a singleton into client.
        String userName; // for no real purpose

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
