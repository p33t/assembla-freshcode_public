package biz.freshcode.learn.gwt.client;

import biz.freshcode.learn.gwt.client.bug.AreaSeriesSpriteBug;
import biz.freshcode.learn.gwt.client.bug.DateFieldBlur;
import biz.freshcode.learn.gwt.client.bug.GridScrollBug;
import biz.freshcode.learn.gwt.client.bug.WindowMoveBug;
import biz.freshcode.learn.gwt.client.bug.contentpanelsize.ContentPanelSizeBug;
import biz.freshcode.learn.gwt.client.bug.dateaccessbug.DateAccessBug;
import biz.freshcode.learn.gwt.client.experiment.Resizer;
import biz.freshcode.learn.gwt.client.experiment.appearance.AppearanceDemo;
import biz.freshcode.learn.gwt.client.experiment.busy.BusyDemo;
import biz.freshcode.learn.gwt.client.experiment.celltable.CellTableDemo;
import biz.freshcode.learn.gwt.client.experiment.chart.ChartDemo;
import biz.freshcode.learn.gwt.client.experiment.chart.area.AreaChartDemo;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt.GanttDemo;
import biz.freshcode.learn.gwt.client.experiment.chart.gantt2.Gantt2Demo;
import biz.freshcode.learn.gwt.client.experiment.chart.mixed.MixedChartDemo;
import biz.freshcode.learn.gwt.client.experiment.chart.mixed2.MixedChart2Demo;
import biz.freshcode.learn.gwt.client.experiment.chart.step.StepChartDemo;
import biz.freshcode.learn.gwt.client.experiment.contextmenu.ContextMenuDemo;
import biz.freshcode.learn.gwt.client.experiment.cookie.CookieDemo;
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
import biz.freshcode.learn.gwt.client.experiment.toolbar.ToolBarDemo;
import biz.freshcode.learn.gwt.client.experiment.tree.TreeDemo;
import biz.freshcode.learn.gwt.client.experiment.triggerfield.TriggerFieldDemo;
import biz.freshcode.learn.gwt.client.experiment.upload.FileUploadDemo;
import biz.freshcode.learn.gwt.client.experiment.window.WindowDemo;
import biz.freshcode.learn.gwt.client.experiment.xtemplate.XTemplateDemo;
import biz.freshcode.learn.gwt.client.uibinder.Basic;
import biz.freshcode.learn.gwt.client.uibinder.Composed;
import biz.freshcode.learn.gwt.client.uibinder.eg.BorderLayoutEg;
import biz.freshcode.learn.gwt.client.uibinder.eg.Tutorial1;
import biz.freshcode.learn.gwt.client.uibinder.eg.Tutorial2;
import biz.freshcode.learn.gwt.client.uispike.gxt.UiSpikePanel;
import biz.freshcode.learn.gwt2.common.client.builder.gwt.DockLayoutPanelBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gwt.MenuBarBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.DialogBuilder;
import biz.freshcode.learn.gwt2.common.client.builder.gxt.container.ViewportBuilder;
import biz.freshcode.learn.gwt2.common.shared.SessionInfo;
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
    Provider<PlaceManager> pmProvider;

    // !!!!! Not sure why need to have SessionInfo as an argument.  Otherwise NPE :(
    @Inject
    public MainPanel(final PlaceController placeController, SessionInfo session) {
        pnl = new DockLayoutPanelBuilder(new DockLayoutPanel(Style.Unit.EM))
                .height("100%")
                .width("100%")
                .addNorth(new MenuBarBuilder()
                        .addItem(new MenuItem("Bugs", subMenu()
                                .addItem(new MenuItem("DateField Blur", (Command) () -> {
                                    IsWidget w = new DateFieldBlur();
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Area Series Sprite", (Command) () -> {
                                    IsWidget w = new AreaSeriesSpriteBug();
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Grid Scroll Always Zero", (Command) () -> {
                                    IsWidget w = new GridScrollBug();
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Window Move Affects Width", (Command) () -> {
                                    IsWidget w = new WindowMoveBug();
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("GXT: Date Edit", (Command) () -> new DateAccessBug().asWidget().show()))
                                .addItem(new MenuItem("ContentPanel Resize", (Command) () -> dialog("Bug", new ContentPanelSizeBug())))
                                .menuBar))
                        .addItem(new MenuItem("RPC", subMenu()
                                .addItem(new MenuItem("Request Factory (again, for testing)", (Command) () -> {
                                    IsWidget w = GWT.create(RequestFactoryDemo.class);
                                    replaceContent(w);
                                }))
                                .menuBar))
                        .addItem(new MenuItem("Experiments", subMenu()
                                .addItem(new MenuItem("Cookie Ops", (Command) () -> {
                                    IsWidget w = GWT.create(CookieDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("File Upload", (Command) () -> {
                                    IsWidget w = GWT.create(FileUploadDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Logging", (Command) () -> {
                                    IsWidget w = GWT.create(LoggingDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Misc.", subMenu()
                                        .addItem(new MenuItem("Hash Code Check", (Command) () -> {
                                            IsWidget w = GWT.create(HashCodeCheck.class);
                                            replaceContent(w);
                                        }))
                                        .menuBar))
                                .addItem(new MenuItem("Field Sync (agressive dirty detect)", (Command) () -> {
                                    IsWidget w = GWT.create(FieldSyncDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Flex Table", (Command) () -> {
                                    IsWidget w = GWT.create(FlexTableDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("JSNI", (Command) () -> {
                                    IsWidget w = GWT.create(JsniDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("IsWidget", (Command) () -> {
                                    IsWidget w = GWT.create(IsWidgetDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("MVP", subMenu()
                                        .addItem(new MenuItem("Home Bake", (Command) () -> {
                                            IsWidget w = GWT.create(HbParent.class);
                                            replaceContent(w);
                                        }))
                                        .addItem(new MenuItem("GWT MVP", (Command) () -> placeController.goTo(new GmPlace(0))))
                                        .addItem(new MenuItem("GWTP (not working)", (Command) () -> {
                                            //noinspection deprecation
                                            pmProvider.get().revealPlace(new PlaceRequest.Builder().nameToken(GmdModule.GMD).build());
                                            replaceContent(new HTML("NOTE: This is not working.  Probably because we are not exclusively GWTP MVP<br/>" +
                                                    "                                        and have other boostrap requirements.<br/>" +
                                                    "                                        It seems we need the ApplicationController.<br/>" +
                                                    "                                        I suspect we can do ApplicationController.init() to bootstrap in our own entry point."));
                                        }))
                                        .menuBar))
                                .addItem(new MenuItem("GWT Grid Demo", (Command) () -> {
                                    IsWidget w = GWT.create(GwtGridDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Grid Window", (Command) () -> new GridWindowDemo().launch()))
                                .addItem(new MenuItem("Request Factory", (Command) () -> {
                                    IsWidget w = GWT.create(RequestFactoryDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Alert", (Command) () -> Window.alert("Consider yourself alerted")))
                                .addItem(new MenuItem("Mouse Over", (Command) () -> dialog("Mouse Over", new MouseOverWidget())))
                                .addItem(new MenuItem("Resizer", (Command) () -> {
                                    Widget w = GWT.create(Resizer.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Ui Binder", subMenu()
                                        .addItem(new MenuItem("Basic", (Command) () -> {
                                            Widget w = GWT.create(Basic.class);
                                            replaceContent(w);
                                        }))
                                        .addItem(new MenuItem("Composed", (Command) () -> {
                                            Widget w = GWT.create(Composed.class);
                                            replaceContent(w);
                                        }))
                                        .menuBar))
                                .menuBar))
                        .addItem(new MenuItem("GXT", subMenu()
                                .addItem(new MenuItem("Layout", (Command) () -> {
                                    IsWidget w = GWT.create(LayoutDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Busy", (Command) () -> {
                                    IsWidget w = GWT.create(BusyDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Tree", (Command) () -> {
                                    IsWidget w = GWT.create(TreeDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Trigger Field", (Command) () -> {
                                    IsWidget w = GWT.create(TriggerFieldDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Charts", subMenu()
                                        .addItem(new MenuItem("Basic Chart", (Command) () -> {
                                            IsWidget w = GWT.create(ChartDemo.class);
                                            replaceContent(w);
                                        }))
                                        .addItem(new MenuItem("Area Chart", (Command) () -> {
                                            IsWidget w = GWT.create(AreaChartDemo.class);
                                            replaceContent(w);
                                        }))
                                        .addItem(new MenuItem("Mixed Chart", (Command) () -> {
                                            IsWidget w = GWT.create(MixedChartDemo.class);
                                            replaceContent(w);
                                        }))
                                        .addItem(new MenuItem("Mixed Chart 2", (Command) () -> {
                                            IsWidget w = GWT.create(MixedChart2Demo.class);
                                            replaceContent(w);
                                        }))
                                        .addItem(new MenuItem("Gantt Chart", (Command) () -> {
                                            IsWidget w = GWT.create(GanttDemo.class);
                                            replaceContent(w);
                                        }))
                                        .addItem(new MenuItem("Gantt Chart2", (Command) () -> {
                                            IsWidget w = GWT.create(Gantt2Demo.class);
                                            replaceContent(w);
                                        }))
                                        .addItem(new MenuItem("Step Chart", (Command) () -> {
                                            IsWidget w = GWT.create(StepChartDemo.class);
                                            replaceContent(w);
                                        }))
                                        .menuBar))
                                .addItem(new MenuItem("Context Menu", (Command) () -> {
                                    IsWidget w = GWT.create(ContextMenuDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Dynamic Layout", (Command) () -> {
                                    IsWidget w = GWT.create(DynamicLayoutDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Popup Form Field", (Command) () -> {
                                    IsWidget w = GWT.create(PopFieldDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Windows (Popup)", (Command) () -> {
                                    IsWidget w = GWT.create(WindowDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Appearance", (Command) () -> {
                                    IsWidget w = GWT.create(AppearanceDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("XTemplate", (Command) () -> {
                                    IsWidget w = GWT.create(XTemplateDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Tool Bar", (Command) () -> {
                                    IsWidget w = GWT.create(ToolBarDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Hover Widgets", (Command) () -> {
                                    IsWidget w = GWT.create(HoverWidgetDemo.class);
                                    replaceContent(w);
                                }))
                                .addItem(new MenuItem("Cell Table Interop Demo", (Command) () -> {
                                    IsWidget w = GWT.create(CellTableDemo.class);
                                    replaceRoot(w);
                                }))
                                .addItem(new MenuItem("Grid Demo", (Command) () -> {
                                    IsWidget w = GWT.create(GxtGridDemo.class);
                                    replaceRoot(w);
                                }))
                                .addItem(new MenuItem("Grid Group By Demo", (Command) () -> {
                                    IsWidget w = GWT.create(GridGroupByDemo.class);
                                    replaceRoot(w);
                                }))
                                .addItem(new MenuItem("UI Spike", subMenu()
                                        .addItem(new MenuItem("UiBuilder", (Command) () -> {
                                            IsWidget w = GWT.create(UiSpikePanel.class);
                                            replaceRoot(w);
                                        }))
                                        .addItem(new MenuItem("Non UiBuilder", (Command) () -> {
                                            IsWidget w = GWT.create(biz.freshcode.learn.gwt.client.uispike.nonuibuilder.UiSpikePanel.class);
                                            replaceRoot(w);
                                        }))
                                        .menuBar))
                                .addItem(new MenuItem("Ui Binder", subMenu()
                                        .addItem(new MenuItem("Border Eg", (Command) () -> {
                                            IsWidget w = GWT.create(BorderLayoutEg.class);
                                            replaceRoot(w);
                                        }))
                                        .addItem(new MenuItem("Tutorial 1 (simple frame)", (Command) () -> {
                                            IsWidget w = GWT.create(Tutorial1.class);
                                            replaceRoot(w);
                                        }))
                                        .addItem(new MenuItem("Tutorial 2 (menu + event)", (Command) () -> {
                                            IsWidget w = GWT.create(Tutorial2.class);
                                            replaceRoot(w);
                                        }))
                                        .menuBar))
                                .addItem(new MenuItem("Forms", (Command) () -> {
                                    IsWidget w = GWT.create(FormsDemo.class);
                                    replaceContent(w.asWidget());
                                }))
                                .addItem(new MenuItem("Forms 2nd Attempt", (Command) () -> {
                                    IsWidget w = GWT.create(Forms2Demo.class);
                                    replaceContent(w.asWidget());
                                }))
                                .addItem(new MenuItem("Forms 3rd Attempt", (Command) () -> {
                                    IsWidget w = GWT.create(Forms3Demo.class);
                                    replaceContent(w.asWidget());
                                }))
                                .addItem(new MenuItem("Drag 'n Drop", (Command) () -> {
                                    IsWidget w = GWT.create(DndUi.class);
                                    replaceContent(w.asWidget());
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
                .heading(heading)
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
