package spittr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpittleFeedServiceImpl implements SpittleFeedService {

	private SimpMessagingTemplate messaging;
	private Pattern pattern = Pattern.compile("\\@(\\S+)");
	
	@Autowired
	public SpittleFeedServiceImpl(SimpMessagingTemplate messaging) {
		this.messaging = messaging;
	}
	
	public void broadcastSpittle(Spittle spittle) {
		messaging.convertAndSend("/topic/spittlefeed", spittle);
		
		Matcher matcher = pattern.matcher(spittle.getMessage());
		if (matcher.find()) {
			String username = matcher.group(1);
			messaging.convertAndSendToUser(username, "/queue/notifications",
					new Notification("You just got mentioned!"));
		}
	}
	
}
