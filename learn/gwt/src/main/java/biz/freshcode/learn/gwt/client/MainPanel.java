package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.bug.GridScrollBug;
import biz.freshcode.learn.gwt.client.bug.WindowMoveBug;
import biz.freshcode.learn.gwt.client.bug.contentpanelsize.ContentPanelSizeBug;
import biz.freshcode.learn.gwt.client.bug.dateaccessbug.DateAccessBug;
import biz.freshcode.learn.gwt.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt.client.experiment.Resizer;
import biz.freshcode.learn.gwt.client.experiment.appearance.AppearanceDemo;
import biz.freshcode.learn.gwt.client.experiment.celltable.CellTableDemo;
import biz.freshcode.learn.gwt.client.experiment.dnd.DndUi;
import biz.freshcode.learn.gwt.client.experiment.forms.Landing;
import biz.freshcode.learn.gwt.client.experiment.grid.GwtGridDemo;
import biz.freshcode.learn.gwt.client.experiment.grid.GxtGridDemo;
import biz.freshcode.learn.gwt.client.experiment.grid.window.GridWindowDemo;
import biz.freshcode.learn.gwt.client.experiment.hoverwidget.HoverWidgetDemo;
import biz.freshcode.learn.gwt.client.experiment.mouseover.MouseOverWidget;
import biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp.GmPlace;
import biz.freshcode.learn.gwt.client.experiment.mvp.homebake.HbParent;
import biz.freshcode.learn.gwt.client.experiment.requestfactory.RequestFactoryDemo;
import biz.freshcode.learn.gwt.client.experiment.resources.ResourcesDemo;
import biz.freshcode.learn.gwt.client.experiment.toolbar.ToolBarDemo;
import biz.freshcode.learn.gwt.client.experiment.window.WindowDemo;
import biz.freshcode.learn.gwt.client.experiment.xtemplate.XTemplateDemo;
import biz.freshcode.learn.gwt.client.uibinder.Basic;
import biz.freshcode.learn.gwt.client.uibinder.Composed;
import biz.freshcode.learn.gwt.client.uibinder.eg.BorderLayoutEg;
import biz.freshcode.learn.gwt.client.uibinder.eg.Tutorial1;
import biz.freshcode.learn.gwt.client.uibinder.eg.Tutorial2;
import biz.freshcode.learn.gwt.client.uispike.builder.MenuBarBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.ViewportBuilder;
import biz.freshcode.learn.gwt.client.uispike.builder.container.DockLayoutPanelBuilder;
import biz.freshcode.learn.gwt.client.uispike.gxt.UiSpikePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MainPanel extends Composite implements AcceptsOneWidget {
    IsWidget content;
    DockLayoutPanel pnl;

    @Inject
    public MainPanel(final PlaceController placeController) {
        pnl = new DockLayoutPanelBuilder(new DockLayoutPanel(Style.Unit.EM))
                .height("100%")
                .width("100%")
                .addNorth(new MenuBarBuilder()
                        .addItem(new MenuItem("Bugs", subMenu()
                                .addItem(new MenuItem("Grid Scroll Always Zero", new Command() {
                                    public void execute() {
                                        IsWidget w = new GridScrollBug();
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Window Move Affects Width", new Command() {
                                    public void execute() {
                                        IsWidget w = new WindowMoveBug();
                                        replaceContent(w);
                                    }
                                }))
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
                                .addItem(new MenuItem("MVP", subMenu()
                                        .addItem(new MenuItem("Home Bake", new Command() {
                                            public void execute() {
                                                IsWidget w = GWT.create(HbParent.class);
                                                replaceContent(w);
                                            }
                                        }))
                                        .addItem(new MenuItem("GWT MVP", new Command() {
                                            public void execute() {
                                                placeController.goTo(new GmPlace(0));
                                            }
                                        }))
                                        .menuBar))
                                .addItem(new MenuItem("Resources", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(ResourcesDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Grid Demo", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(GwtGridDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Grid Window", new Command() {
                                    public void execute() {
                                        new GridWindowDemo().launch();
                                    }
                                }))
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
                                .addItem(new MenuItem("Windows (Popup)", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(WindowDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Appearance", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(AppearanceDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("XTemplate", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(XTemplateDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Tool Bar", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(ToolBarDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Hover Widgets", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(HoverWidgetDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Cell Table Interop Demo", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(CellTableDemo.class);
                                        replaceRoot(w);
                                    }
                                }))
                                .addItem(new MenuItem("Grid Demo", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(GxtGridDemo.class);
                                        replaceRoot(w);
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
        initWidget(pnl);
    }

    @Override
    public void setWidget(IsWidget w) {
        replaceContent(w);
    }

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

    private MenuBarBuilder subMenu() {
        return new MenuBarBuilder(new MenuBar(true));
    }

    private void replaceRoot(IsWidget w) {
        // NOTE: If using entire display area use RootLayoutPanel (not RootPanel)
        RootLayoutPanel root = EntryPoint.getRoot();
        root.clear();
        root.add(new ViewportBuilder()
                .widget(w)
                .viewport);
    }

    private void replaceContent(IsWidget newContent) {
        // smart way to decide if is root.  Migrate to this gradually.
        if (newContent instanceof IsRootContent) {
            replaceRoot(newContent);
            return;
        }
        if (newContent == null) newContent = new HTMLPanel("<p>NULL Content :(</p>");
        pnl.remove(content);
        content = newContent;
        pnl.add(content);
    }
}
