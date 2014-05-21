package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.bug.*;
import biz.freshcode.learn.gwt.client.bug.contentpanelsize.ContentPanelSizeBug;
import biz.freshcode.learn.gwt.client.bug.dateaccessbug.DateAccessBug;
import biz.freshcode.learn.gwt.client.builder.gwt.DockLayoutPanelBuilder;
import biz.freshcode.learn.gwt.client.builder.gwt.MenuBarBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt.client.builder.gxt.container.ViewportBuilder;
import biz.freshcode.learn.gwt.client.experiment.Resizer;
import biz.freshcode.learn.gwt.client.experiment.appearance.AppearanceDemo;
import biz.freshcode.learn.gwt.client.experiment.busy.BusyDemo;
import biz.freshcode.learn.gwt.client.experiment.celltable.CellTableDemo;
import biz.freshcode.learn.gwt.client.experiment.chart.ChartDemo;
import biz.freshcode.learn.gwt.client.experiment.chart.area.AreaChartDemo;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.GanttDemo;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.Gantt2Demo;
import biz.freshcode.learn.gwt.client.experiment.chart.lanes.LanesChartDemo;
import biz.freshcode.learn.gwt.client.experiment.chart.mixed.MixedChartDemo;
import biz.freshcode.learn.gwt.client.experiment.chart.mixed2.MixedChart2Demo;
import biz.freshcode.learn.gwt.client.experiment.chart.step.StepChartDemo;
import biz.freshcode.learn.gwt.client.experiment.contextmenu.ContextMenuDemo;
import biz.freshcode.learn.gwt.client.experiment.dnd.DndUi;
import biz.freshcode.learn.gwt.client.experiment.dynamiclayout.DynamicLayoutDemo;
import biz.freshcode.learn.gwt.client.experiment.fieldsync.FieldSyncDemo;
import biz.freshcode.learn.gwt.client.experiment.flextable.FlexTableDemo;
import biz.freshcode.learn.gwt.client.experiment.forms.FormsDemo;
import biz.freshcode.learn.gwt.client.experiment.forms2.Forms2Demo;
import biz.freshcode.learn.gwt.client.experiment.forms3.Forms3Demo;
import biz.freshcode.learn.gwt.client.experiment.grid.GwtGridDemo;
import biz.freshcode.learn.gwt.client.experiment.grid.GxtGridDemo;
import biz.freshcode.learn.gwt.client.experiment.grid.window.GridWindowDemo;
import biz.freshcode.learn.gwt.client.experiment.gridgroupby.GridGroupByDemo;
import biz.freshcode.learn.gwt.client.experiment.hoverwidget.HoverWidgetDemo;
import biz.freshcode.learn.gwt.client.experiment.iswidget.IsWidgetDemo;
import biz.freshcode.learn.gwt.client.experiment.jsni.JsniDemo;
import biz.freshcode.learn.gwt.client.experiment.layout.LayoutDemo;
import biz.freshcode.learn.gwt.client.experiment.logging.LoggingDemo;
import biz.freshcode.learn.gwt.client.experiment.miscellaneous.HashCodeCheck;
import biz.freshcode.learn.gwt.client.experiment.mouseover.MouseOverWidget;
import biz.freshcode.learn.gwt.client.experiment.mvp.gwtmvp.GmPlace;
import biz.freshcode.learn.gwt.client.experiment.mvp.gwtp.GmdModule;
import biz.freshcode.learn.gwt.client.experiment.mvp.homebake.HbParent;
import biz.freshcode.learn.gwt.client.experiment.popfield.PopFieldDemo;
import biz.freshcode.learn.gwt.client.experiment.requestfactory.RequestFactoryDemo;
import biz.freshcode.learn.gwt.client.experiment.resources.ResourcesDemo;
import biz.freshcode.learn.gwt.client.experiment.toolbar.ToolBarDemo;
import biz.freshcode.learn.gwt.client.experiment.tree.TreeDemo;
import biz.freshcode.learn.gwt.client.experiment.triggerfield.TriggerFieldDemo;
import biz.freshcode.learn.gwt.client.experiment.window.WindowDemo;
import biz.freshcode.learn.gwt.client.experiment.xtemplate.XTemplateDemo;
import biz.freshcode.learn.gwt.client.inject.SessionInfo;
import biz.freshcode.learn.gwt.client.rpc.dispatch.DispatchDemo;
import biz.freshcode.learn.gwt.client.rpc.dispatch.SecureDispatchDemo;
import biz.freshcode.learn.gwt.client.rpc.greet.GreetRpcDemo;
import biz.freshcode.learn.gwt.client.uibinder.Basic;
import biz.freshcode.learn.gwt.client.uibinder.Composed;
import biz.freshcode.learn.gwt.client.uibinder.eg.BorderLayoutEg;
import biz.freshcode.learn.gwt.client.uibinder.eg.Tutorial1;
import biz.freshcode.learn.gwt.client.uibinder.eg.Tutorial2;
import biz.freshcode.learn.gwt.client.uispike.gxt.UiSpikePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

@Singleton
public class MainPanel extends Composite implements AcceptsOneWidget {
    IsWidget content;
    DockLayoutPanel pnl;
    @Inject
    Provider<DispatchDemo> ddProvider;
    @Inject
    Provider<SecureDispatchDemo> sdProvider;
    @Inject
    Provider<PlaceManager> pmProvider;

    // !!!!! Not sure why need to have SessionInfo as an argument.  Otherwise NPE :(
    @Inject
    public MainPanel(final PlaceController placeController, SessionInfo session) {
        pnl = new DockLayoutPanelBuilder(new DockLayoutPanel(Style.Unit.EM))
                .height("100%")
                .width("100%")
                .addNorth(new MenuBarBuilder()
                        .addItem(new MenuItem("Bugs", subMenu()
                                .addItem(new MenuItem("DateField Blur", new Command() {
                                    public void execute() {
                                        IsWidget w = new DateFieldBlur();
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Area Series Sprite", new Command() {
                                    public void execute() {
                                        IsWidget w = new AreaSeriesSpriteBug();
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Inline Grid Edit Combo Blur", new Command() {
                                    public void execute() {
                                        IsWidget w = new GridEditInlineComboBoxBlurBug();
                                        replaceContent(w);
                                    }
                                }))
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
                        .addItem(new MenuItem("RPC", subMenu()
                                .addItem(new MenuItem("Dispatch - Secure", new Command() {
                                    public void execute() {
                                        IsWidget w = sdProvider.get();
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Dispatch", new Command() {
                                    public void execute() {
                                        IsWidget w = ddProvider.get();
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Simple Greet", new Command() {
                                    public void execute() {
                                        GreetRpcDemo w = GWT.create(GreetRpcDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Request Factory (again, for testing)", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(RequestFactoryDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .menuBar))
                        .addItem(new MenuItem("Experiments", subMenu()
                                .addItem(new MenuItem("Logging", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(LoggingDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Misc.", subMenu()
                                        .addItem(new MenuItem("Hash Code Check", new Command() {
                                            public void execute() {
                                                IsWidget w = GWT.create(HashCodeCheck.class);
                                                replaceContent(w);
                                            }
                                        }))
                                        .menuBar))
                                .addItem(new MenuItem("Field Sync (agressive dirty detect)", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(FieldSyncDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Flex Table", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(FlexTableDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("JSNI", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(JsniDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("IsWidget", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(IsWidgetDemo.class);
                                        replaceContent(w);
                                    }
                                }))
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
                                        .addItem(new MenuItem("GWTP (not working)", new Command() {
                                            public void execute() {
                                                //noinspection deprecation
                                                pmProvider.get().revealPlace(new PlaceRequest(GmdModule.GMD));
                                                replaceContent(new HTML("NOTE: This is not working.  Probably because we are not exclusively GWTP MVP<br/>" +
                                                        "                                        and have other boostrap requirements.<br/>" +
                                                        "                                        It seems we need the ApplicationController.<br/>" +
                                                        "                                        I suspect we can do ApplicationController.init() to bootstrap in our own entry point."));
                                            }
                                        }))
                                        .menuBar))
                                .addItem(new MenuItem("Resources", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(ResourcesDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("GWT Grid Demo", new Command() {
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
                                .addItem(new MenuItem("Layout", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(LayoutDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Busy", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(BusyDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Tree", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(TreeDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Trigger Field", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(TriggerFieldDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Charts", subMenu()
                                        .addItem(new MenuItem("Basic Chart", new Command() {
                                            public void execute() {
                                                IsWidget w = GWT.create(ChartDemo.class);
                                                replaceContent(w);
                                            }
                                        }))
                                        .addItem(new MenuItem("Area Chart", new Command() {
                                            public void execute() {
                                                IsWidget w = GWT.create(AreaChartDemo.class);
                                                replaceContent(w);
                                            }
                                        }))
                                        .addItem(new MenuItem("Mixed Chart", new Command() {
                                            public void execute() {
                                                IsWidget w = GWT.create(MixedChartDemo.class);
                                                replaceContent(w);
                                            }
                                        }))
                                        .addItem(new MenuItem("Mixed Chart 2", new Command() {
                                            public void execute() {
                                                IsWidget w = GWT.create(MixedChart2Demo.class);
                                                replaceContent(w);
                                            }
                                        }))
                                        .addItem(new MenuItem("Gantt Chart", new Command() {
                                            public void execute() {
                                                IsWidget w = GWT.create(GanttDemo.class);
                                                replaceContent(w);
                                            }
                                        }))
                                        .addItem(new MenuItem("Gantt Chart2", new Command() {
                                            public void execute() {
                                                IsWidget w = GWT.create(Gantt2Demo.class);
                                                replaceContent(w);
                                            }
                                        }))
                                        .addItem(new MenuItem("Lanes Chart (Gantt3)", new Command() {
                                            public void execute() {
                                                IsWidget w = GWT.create(LanesChartDemo.class);
                                                replaceContent(w);
                                            }
                                        }))
                                        .addItem(new MenuItem("Step Chart", new Command() {
                                            public void execute() {
                                                IsWidget w = GWT.create(StepChartDemo.class);
                                                replaceContent(w);
                                            }
                                        }))
                                        .menuBar))
                                .addItem(new MenuItem("Context Menu", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(ContextMenuDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Dynamic Layout", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(DynamicLayoutDemo.class);
                                        replaceContent(w);
                                    }
                                }))
                                .addItem(new MenuItem("Popup Form Field", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(PopFieldDemo.class);
                                        replaceContent(w);
                                    }
                                }))
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
                                .addItem(new MenuItem("Grid Group By Demo", new Command() {
                                    public void execute() {
                                        IsWidget w = GWT.create(GridGroupByDemo.class);
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
                                        IsWidget w = GWT.create(FormsDemo.class);
                                        replaceContent(w.asWidget());
                                    }
                                }))
                                .addItem(new MenuItem("Forms 2nd Attempt", new Command() {
                                    @Override
                                    public void execute() {
                                        IsWidget w = GWT.create(Forms2Demo.class);
                                        replaceContent(w.asWidget());
                                    }
                                }))
                                .addItem(new MenuItem("Forms 3rd Attempt", new Command() {
                                    @Override
                                    public void execute() {
                                        IsWidget w = GWT.create(Forms3Demo.class);
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
                .add(content = new HTMLPanel("<p>User name is: " + session.getUserName() + "</p>"))
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
        if (content != null) content.asWidget().removeFromParent();
        content = newContent;
        pnl.add(content);
    }
}
