package concert;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("concert.xml");
        Performance performance = context.getBean(Performance.class);
        performance.perform();
        context.close();
    }
}
