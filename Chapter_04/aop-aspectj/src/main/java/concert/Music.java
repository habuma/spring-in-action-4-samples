package concert;

import org.springframework.stereotype.Component;

@Component
public class Music implements Performance {
    @Override
    public void perform() {
        System.out.println("Piano Solo");
    }
}
