package biz.freshcode.learn.gwt2.common.client.util.data;

import com.sencha.gxt.data.shared.ModelKeyProvider;

public interface HasId {
    public static final ModelKeyProvider<HasId> ID_PROVIDER = new ModelKeyProvider<HasId>() {
        @Override
        public String getKey(HasId item) {
            return item.getId();
        }
    };

    String getId();

    public static class Util {
        private static int nextId = 1;

        public static String nextId() {
            return "id-" + nextId++;
        }
    }
}
