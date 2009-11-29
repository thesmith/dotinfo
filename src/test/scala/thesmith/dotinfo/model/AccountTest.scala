package thesmith.dotinfo.model

import org.junit._
import Assert._

@Test
class AccountTest {
  val account = new Account()

  @Before
  def setUp() = {
    account.domain = "domain"
    account.personId = "thesmith"
    account.username = "thesmith"
  }

  @Test
  def shouldPersistAccount() = {
    Model.persistAndFlush(account)
    assertNotNull(account.id)
  }

  @Test
  def shouldFindAccount() = {
    Model.persistAndFlush(account)
    val acc = Model.find(classOf[Account], account.id)
    assertNotNull(acc)
    assertFalse(acc.isEmpty)
    assertEquals(account.username, acc.get.username)
  }

  @Test
  def shouldFindAccounts() = {
    Model.persistAndFlush(account)
    val accs = Model.createQuery[Account]("from Account").getResultList
      
    assertNotNull(accs)
    assertFalse(accs.isEmpty)
    assertTrue(accs.size > 0)

    accs.foreach((acc: Account) => assertNotNull(acc.username))
  }
}
