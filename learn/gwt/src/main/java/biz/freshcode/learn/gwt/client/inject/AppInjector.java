package biz.freshcode.learn.gwt.client.inject;

import biz.freshcode.learn.gwt.client.EntryPoint;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(AppModule.class)
public interface AppInjector extends Ginjector {
    EntryPoint.Bootstrap bootstrap();
}
