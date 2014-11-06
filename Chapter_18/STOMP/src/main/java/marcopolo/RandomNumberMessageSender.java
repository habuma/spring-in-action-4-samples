package marcopolo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RandomNumberMessageSender {

  private SimpMessagingTemplate messaging;

  @Autowired
  public RandomNumberMessageSender(SimpMessagingTemplate messaging) {
    this.messaging = messaging;
  }
  
  @Scheduled(fixedRate=10000)
  public void sendRandomNumber() {
    Shout random = new Shout();
    random.setMessage("Random # : " + (Math.random() * 100));
    messaging.convertAndSend("/topic/marco", random);
  }
  
}
