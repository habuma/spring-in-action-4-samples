package sia.knights;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sia.knights.Knight;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class KnightXMLInjectionTest {

  @Autowired
  Knight knight;

  @Autowired
  FakePrintStream printStream;

  @After
  public void clearPrintStream() {
    printStream.clear();
  }

  @Test
  public void shouldInjectKnightWithSlayDragonQuest() {
    knight.embarkOnQuest();
    assertEquals(
        "Fa la la, the knight is so brave!\n" +
        "Embarking on quest to slay the dragon!\n" +
        "Tee hee hee, the brave knight did embark on a quest!\n", 
        printStream.getPrintedString());
  }

}
