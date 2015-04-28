package biz.freshcode.learn.gwt2.common.client.util.data;

import com.sencha.gxt.core.client.ValueProvider;

public interface HasIdTitle extends HasId {
    public static final ValueProvider<HasIdTitle, String> TITLE_PROVIDER = new ValueProvider<HasIdTitle, String>() {
        @Override
        public String getValue(HasIdTitle object) {
            return object.getTitle();
        }

        @Override
        public void setValue(HasIdTitle object, String value) {
            throw new RuntimeException("setValue not implemented");
        }

        @Override
        public String getPath() {
            return "title";
        }
    };

    String getTitle();

    /**
     * A default implementation for utility purposes.
     */
    public static class Impl implements HasIdTitle {
        private final String id;
        private final String title;

        public Impl(String uniqueTitle) {
            this(uniqueTitle, uniqueTitle);
        }

        public Impl(String id, String title) {
            this.id = id;
            this.title = title;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getTitle() {
            return title;
        }
    }
}
