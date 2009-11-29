package thesmith.dotinfo.service

import thesmith.dotinfo.model._

/** Service provides interface to Account Entity */
class AccountService {
  /** Persist an account */
  def create(account: Account) = Model.persistAndFlush(account)

  /** Remove an account */
  def delete(personId: String, domain: String) = {
    Model.createQuery("delete from Account ac where ac.personId = :personId and ac.domain = :domain")
      .setParameter("personId", personId)
      .setParameter("domain", domain)
      .executeUpdate()
  }

  /** Get all of a person's accounts */
  def list(personId: String): Seq[Account] = Model.createQuery[Account]("from Account ac where ac.personId = :personId")
    .setParameter("personId", personId).getResultList

  /** Get a person's account for a specific domain */
  def find(personId: String, domain: String): Account = {
    val account = Model.createQuery[Account]("from Account ac where ac.personId = :personId and ac.domain = :domain")
      .setParameter("personId", personId)
      .setParameter("domain", domain)
      .findOne
    account.get
  }
}
