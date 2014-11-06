package marcopolo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MarcoController {

  private static final Logger logger = LoggerFactory
      .getLogger(MarcoController.class);

  @MessageMapping("/marco")
  public Shout handleShout(Shout incoming) {
    logger.info("Received message: " + incoming.getMessage());

    try { Thread.sleep(2000); } catch (InterruptedException e) {}
    
    Shout outgoing = new Shout();
    outgoing.setMessage("Polo!");
    
    return outgoing;
  }

}
