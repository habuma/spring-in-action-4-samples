package spittr.alerts;

import spittr.domain.Spittle;

public class SpittleAlertHandler {
  
  public void handleSpittleAlert(Spittle spittle) {
    System.out.println(spittle.getMessage());
  }

}
