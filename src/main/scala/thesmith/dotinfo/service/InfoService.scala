package thesmith.dotinfo.service

import java.net._
import org.joda.time.{DateTime, Interval};
import scala.xml._

import thesmith.dotinfo.model._

/** Trait defines interface to retireve status information from Info services */
trait InfoService {
  /** Persist an info */
  def create(info: Info) = Model.persistAndFlush(info)

  /** Remove an info */
  def delete(personId: String, domain: String) = {
    Model.createQuery("delete from Info i where i.personId = :personId and i.domain = :domain")
      .setParameter("personId", personId)
      .setParameter("domain", domain)
      .executeUpdate()
  }

  /** Get all of a person's infos */
  def list(personId: String): Seq[Info] = 
    Model.createQuery[Info]("from Info i where i.personId = :personId")
    .setParameter("personId", personId).getResultList

  /** Get all of a person's infos */
  def list(personId: String, domain: String): Seq[Info] = 
    Model.createQuery[Info]("from Info i where i.personId = :personId and i.domain = :domain")
    .setParameter("personId", personId)
    .setParameter("domain", domain)
    .getResultList

  /** Get a person's info for a specific domain */
  def find(personId: String, domain: String, ago: DateTime): Info = 
    Model.createQuery[Info]("from Info i where i.personId = :personId and i.domain = :domain and i.ago = :ago")
    .setParameter("personId", personId)
    .setParameter("domain", domain)
    .setParameter("ago", ago)
    .getSingleResult

  /** Retrieve status information from Info service */
  def status(name: String): Info
  
  protected def ago(created: DateTime): String = {
    val period = new Interval(created, new DateTime).toPeriod()
    val minutes = period.getMinutes
    
    var ago: String = ""
    ago+= printPeriod(period.getYears, "year")
    ago+= printPeriod(period.getMonths, "month")
    ago+= printPeriod(period.getDays, "day")
    ago+= printPeriod(period.getHours, "hour")
    
    if (ago != "") ago = ago.substring(0, ago.length-2)
    
    if (minutes > 0) {
      if (ago != "") ago+= " and "
      ago+= minutes
      ago+= " minute"
      if (minutes != 1) ago+= "s"
    }
    ago+= " ago"
    
    return ago
  }
  
  protected def printPeriod(value: Int, desc: String): String = {
    if (value > 1) {
      return value+" "+desc+"s, "
    } else if (value == 1) {
      return value+" "+desc+", "
    } else {
      return ""
    }
  }
  
  protected def getXML(urlString: String): Elem = {
    val url = new URL(urlString)
    val conn = url.openConnection
    XML.load(conn.getInputStream)
  }
}
