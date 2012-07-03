package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.bug.contentpanelsize.ContentPanelSizeBug;
import biz.freshcode.learn.gwt.client.bug.dateaccessbug.DateAccessBug;
import biz.freshcode.learn.gwt.client.experiment.Resizer;
import biz.freshcode.learn.gwt.client.experiment.celltable.CellTableDemo;
import biz.freshcode.learn.gwt.client.experiment.dnd.DndUi;
import biz.freshcode.learn.gwt.client.experiment.forms.Landing;
import biz.freshcode.learn.gwt.client.experiment.grid.GxtGridDemo;
import biz.freshcode.learn.gwt.client.experiment.mouseover.MouseOverWidget;
import biz.freshcode.learn.gwt.client.experiment.requestfactory.RequestFactoryDemo;
import biz.freshcode.learn.gwt.client.uibinder.Basic;
import biz.freshcode.learn.gwt.client.uibinder.Composed;
import biz.freshcode.learn.gwt.client.uibinder.eg.BorderLayoutEg;
import biz.freshcode.learn.gwt.client.uibinder.eg.Tutorial1;
import biz.freshcode.learn.gwt.client.uibinder.eg.Tutorial2;
import biz.freshcode.learn.gwt.client.uispike.builder.DialogBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.MenuBarBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.ViewportBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.DockLayoutPanelBuilder;
import biz.freshcode.learn.gwt.client.uispike.gxt.UiSpikePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class MainPanel extends Composite {
    IsWidget content;
    DockLayoutPanel pnl = new DockLayoutPanelBuilder(new DockLayoutPanel(Style.Unit.EM))
            .height("100%")
            .width("100%")
            .addNorth(new MenuBarBuilder()
                    .addItem(new MenuItem("Bugs", subMenu()
                            .addItem(new MenuItem("GXT: Date Edit", new Command() {
                                public void execute() {
                                    new DateAccessBug().asWidget().show();
                                }
                            }))
                            .addItem(new MenuItem("ContentPanel Resize", new Command() {
                                public void execute() {
                                    dialog("Bug", new ContentPanelSizeBug());
                                }
                            }))
                            .menuBar))
                    .addItem(new MenuItem("Experiments", subMenu()
                            .addItem(new MenuItem("Request Factory", new Command() {
                                public void execute() {
                                    IsWidget w = GWT.create(RequestFactoryDemo.class);
                                    replaceContent(w);
                                }
                            }))
                            .addItem(new MenuItem("Alert", new Command() {
                                public void execute() {
                                    Window.alert("Consider yourself alerted");
                                }
                            }))
                            .addItem(new MenuItem("Mouse Over", new Command() {
                                public void execute() {
                                    dialog("Mouse Over", new MouseOverWidget());
                                }
                            }))
                            .addItem(new MenuItem("Resizer", new Command() {
                                public void execute() {
                                    Widget w = GWT.create(Resizer.class);
                                    replaceContent(w);
                                }
                            }))
                            .addItem(new MenuItem("Ui Binder", subMenu()
                                    .addItem(new MenuItem("Basic", new Command() {
                                        public void execute() {
                                            Widget w = GWT.create(Basic.class);
                                            replaceContent(w);
                                        }
                                    }))
                                    .addItem(new MenuItem("Composed", new Command() {
                                        public void execute() {
                                            Widget w = GWT.create(Composed.class);
                                            replaceContent(w);
                                        }
                                    }))
                                    .menuBar))
                            .menuBar))
                    .addItem(new MenuItem("GXT", subMenu()
                            .addItem(new MenuItem("Cell Table Interop Demo", new Command() {
                                public void execute() {
                                    IsWidget w = GWT.create(CellTableDemo.class);
                                    replaceRoot(w);
                                }
                            }))
                            .addItem(new MenuItem("Grid Demo", new Command() {
                                public void execute() {
                                    IsWidget w = GWT.create(GxtGridDemo.class);
                                    replaceContent(w);
                                }
                            }))
                            .addItem(new MenuItem("UI Spike", subMenu()
                                    .addItem(new MenuItem("UiBuilder", new Command() {
                                        public void execute() {
                                            IsWidget w = GWT.create(UiSpikePanel.class);
                                            replaceRoot(w);
                                        }
                                    }))
                                    .addItem(new MenuItem("Non UiBuilder", new Command() {
                                        public void execute() {
                                            IsWidget w = GWT.create(biz.freshcode.learn.gwt.client.uispike.nonuibuilder.UiSpikePanel.class);
                                            replaceRoot(w);
                                        }
                                    }))
                                    .menuBar))
                            .addItem(new MenuItem("Ui Binder", subMenu()
                                    .addItem(new MenuItem("Border Eg", new Command() {
                                        public void execute() {
                                            IsWidget w = GWT.create(BorderLayoutEg.class);
                                            replaceRoot(w);
                                        }
                                    }))
                                    .addItem(new MenuItem("Tutorial 1 (simple frame)", new Command() {
                                        public void execute() {
                                            IsWidget w = GWT.create(Tutorial1.class);
                                            replaceRoot(w);
                                        }
                                    }))
                                    .addItem(new MenuItem("Tutorial 2 (menu + event)", new Command() {
                                        public void execute() {
                                            IsWidget w = GWT.create(Tutorial2.class);
                                            replaceRoot(w);
                                        }
                                    }))
                                    .menuBar))
                            .addItem(new MenuItem("Forms", new Command() {
                                @Override
                                public void execute() {
                                    IsWidget w = GWT.create(Landing.class);
                                    replaceContent(w.asWidget());
                                }
                            }))
                            .addItem(new MenuItem("Drag 'n Drop", new Command() {
                                @Override
                                public void execute() {
                                    IsWidget w = GWT.create(DndUi.class);
                                    replaceContent(w.asWidget());
                                }
                            }))
                            .menuBar))
                    .menuBar,
                    2)
            .add(content = new HTMLPanel("<p>Center Content</p>"))
            .dockLayoutPanel;

    private void dialog(String heading, IsWidget w) {
        new DialogBuilder()
                .headingText(heading)
                .height(400)
                .width(600)
                .resizable(true)
                .modal(false)
                .add(w)
                .autoHide(true)
                .dialog
                .show();
    }

    {
        initWidget(pnl);
    }

    private MenuBarBuilder subMenu() {
        return new MenuBarBuilder(new MenuBar(true));
    }

    private void replaceRoot(IsWidget w) {
        RootPanel root = RootPanel.get();
        root.clear();
        root.add(new ViewportBuilder()
                .widget(w)
                .viewport);
    }

    private void replaceContent(IsWidget newContent) {
        pnl.remove(content);
        content = newContent;
        pnl.add(content);
    }
}
