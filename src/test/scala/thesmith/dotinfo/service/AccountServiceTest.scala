package thesmith.dotinfo.service

import org.junit._
import Assert._

import thesmith.dotinfo.model._

@Test
class AccountServiceTest {
  val account = new Account()
  val service = new AccountService()

  @Before
  def setUp() = {
    account.domain = "domain"
    account.personId = "thesmith"
    account.username = "thesmith"
  }

  @After
  def clearDown(): Unit = {
    service.delete(account.personId, account.domain)
  }

  @Test
  def shouldPersistAccount() = {
    service.create(account)
    assertNotNull(account.id)
  }

  @Test
  def shouldFindAccount() = {
    service.create(account)
    val acc = service.find(account.personId, account.domain)
    assertNotNull(acc)
    assertEquals(account.username, acc.username)
  }

  @Test
  def shouldFindAccounts() = {
    service.create(account)
    val accs = service.list(account.personId)
      
    assertNotNull(accs)
    assertFalse(accs.isEmpty)
    assertTrue(accs.size > 0)

    accs.foreach((acc: Account) => assertNotNull(acc.username))
  }
}
