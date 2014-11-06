package soundsystem;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ConstructorArgCollectionTest {

  @Rule
  public final StandardOutputStreamLog log = new StandardOutputStreamLog();

  @Autowired
  private MediaPlayer player;

  @Test
  public void play() {
    player.play();
    assertEquals(
        "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles\n" +
        "-Track: Sgt. Pepper's Lonely Hearts Club Band\n" +
        "-Track: With a Little Help from My Friends\n" +
        "-Track: Lucy in the Sky with Diamonds\n" +
        "-Track: Getting Better\n" +
        "-Track: Fixing a Hole\n" +
        "-Track: She's Leaving Home\n" +
        "-Track: Being for the Benefit of Mr. Kite!\n" +
        "-Track: Within You Without You\n" +
        "-Track: When I'm Sixty-Four\n" +
        "-Track: Lovely Rita\n" +
        "-Track: Good Morning Good Morning\n" +
        "-Track: Sgt. Pepper's Lonely Hearts Club Band (Reprise)\n" +
        "-Track: A Day in the Life\n",
        log.getLog());
  }

}
