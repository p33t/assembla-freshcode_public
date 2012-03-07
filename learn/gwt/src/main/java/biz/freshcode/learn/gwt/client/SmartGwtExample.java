package biz.freshcode.learn.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class SmartGwtExample implements EntryPoint {

   public void onModuleLoad()
   {
       IButton button = new IButton("Hello world");
       button.addClickHandler(new ClickHandler()
       {
           public void onClick(ClickEvent event)
           {
               SC.say("Hello World from SmartGWT");
           }
       });

       button.draw();
   }
}
