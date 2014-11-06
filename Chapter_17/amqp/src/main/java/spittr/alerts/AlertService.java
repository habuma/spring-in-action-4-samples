package spittr.alerts;

import spittr.domain.Spittle;

public interface AlertService {

  void sendSpittleAlert(Spittle spittle);

}
