package thesmith.dotinfo.service.lastfm

import org.junit._
import Assert._

/** Twitter service tester. Integration Test. */
@Test
class LastfmServiceTest {
  val service = new LastfmService()
  @Test
  def shouldGetStatus() = {
    val status = service.status("thesmith")
    assertNotNull(status)
    assertNotNull(status.title)
  }
}
