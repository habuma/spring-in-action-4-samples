package spittr.alerts;

import org.springframework.jms.core.JmsOperations;

import spittr.domain.Spittle;

public class AlertServiceImpl implements AlertService {

  private JmsOperations jmsOperations;

  public AlertServiceImpl(JmsOperations jmsOperations) {
    this.jmsOperations = jmsOperations;
  }

//  public void sendSpittleAlert(final Spittle spittle) {
//    jmsOperations.send(
//      "spittle.alert.queue", 
//      new MessageCreator() {
//        public Message createMessage(Session session) 
//                       throws JMSException {
//          return session.createObjectMessage(spittle);
//        }
//      }
//    );
//  }

/*
  public void sendSpittleAlert(final Spittle spittle) {
    jmsOperations.send(
      new MessageCreator() {
        public Message createMessage(Session session) 
                       throws JMSException {
          return session.createObjectMessage(spittle);
        }
      }
    );
  }
*/

  public void sendSpittleAlert(Spittle spittle) {
    jmsOperations.convertAndSend(spittle);
  }
  
//  public Spittle getSpittleAlert() {
//    try {
//    ObjectMessage message = (ObjectMessage) jmsOperations.receive();
//    return (Spittle) message.getObject();
//    } catch (JMSException e) {
//      throw JmsUtils.convertJmsAccessException(e);
//    }
//  }
  
  public Spittle retrieveSpittleAlert() {
    return (Spittle) jmsOperations.receiveAndConvert();
  }

}
