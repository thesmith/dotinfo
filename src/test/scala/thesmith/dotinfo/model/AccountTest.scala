package thesmith.dotinfo.model

import org.junit._
import Assert._

@Test
class AccountTest {
  val account: Account = new Account()

  @Before
  def setUp() = {
    account.domain = "domain"
    account.personId = "thesmith"
    account.username = "thesmith"
    Model.persistAndFlush(account)
  }

  @After
  def tearDown(): Unit = {
    Model.createQuery("delete from Account ac").executeUpdate()
  }

  @Test
  def shouldPersistAccount() = {
    assertNotNull(account.id)
  }

  @Test
  def shouldFindAccount() = {
    val acc = Model.find(classOf[Account], account.id)
    assertNotNull(acc)
    assertFalse(acc.isEmpty)
    assertEquals(account.username, acc.get.username)
  }

  @Test
  def shouldFindAccounts(): Unit = {
    val a = new Account()
    a.personId = "jlkldfs"
    a.domain = "fjkdls"
    a.username = "jfkdls"
    Model.persistAndFlush(a)

    val accs = Model.createQuery[Account]("from Account").getResultList
      
    assertNotNull(accs)
    assertFalse(accs.isEmpty)
    assertEquals(2, accs.size)

    accs.foreach((acc: Account) => assertNotNull(acc.username))
  }
}
