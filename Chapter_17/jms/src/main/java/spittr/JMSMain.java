package spittr;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsOperations;

public class JMSMain {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/messaging.xml");
    JmsOperations jms = context.getBean(JmsOperations.class);
    for(int i=0; i< 10; i++) {
    jms.convertAndSend("hello.queue", "Hello");
    }
    context.close();
  }

}
