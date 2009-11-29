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
    service.create(account)
  }

  @After
  def clearDown(): Unit = {
    service.delete(account.personId, account.domain)
  }

  @Test
  def shouldPersistAccount() = {
    assertNotNull(account.id)
  }

  @Test
  def shouldFindAccount() = {
    val acc = service.find(account.personId, account.domain)
    assertNotNull(acc)
    assertEquals(account.username, acc.username)
  }

  @Test
  def shouldFindAccounts(): Unit = {
    val a = new Account()
    a.personId = account.personId
    a.domain = "somedomain"
    a.username = "fjkdls"

    service.create(a)

    assertNotNull(a.id)
    val acc = service.find(account.personId, account.domain)
    assertNotNull(acc)

    val accs = service.list(account.personId)
      
    assertNotNull(accs)
    assertFalse(accs.isEmpty)
    assertEquals(2, accs.size)

    accs.foreach((acc: Account) => assertNotNull(acc.username))
    service.delete(account.personId, "somedomain")
  }
}
