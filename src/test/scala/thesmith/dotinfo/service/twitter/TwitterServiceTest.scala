package thesmith.dotinfo.service.twitter

import org.junit._
import Assert._

/** Twitter service tester. Integration Test. */
@Test
class TwitterServiceTest {
  val service = new TwitterService()
  @Test
  def shouldGetStatus() = {
    val status = service.status("thesmith")
    assertNotNull(status)
    assertNotNull(status.title)
  }
}
