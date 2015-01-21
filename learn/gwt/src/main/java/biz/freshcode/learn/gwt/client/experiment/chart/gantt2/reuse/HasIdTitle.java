package biz.freshcode.learn.gwt.client.experiment.chart.gantt2.reuse;

public interface HasIdTitle {
    String getId();

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
