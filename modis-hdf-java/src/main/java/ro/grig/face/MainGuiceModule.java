package ro.grig.face;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;

public class MainGuiceModule extends AbstractModule {

   private EventBus eventBus = new EventBus("config");

   @Override
   protected void configure() {
      bind(Sc.class);
      bind(IntroWindow.class);
      bind(MainInterface.class);
      bind(GlobalConfigurationEditor.class);
      bind(RunWsSearch.class);
      bind(RunQueryExtractPoint.class);
      bind(EventBus.class).toInstance(eventBus);
      bind(InterProcesServer.class);
   }
}
