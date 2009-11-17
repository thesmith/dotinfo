package thesmith.dotinfo.service

import java.net._
import org.joda.time.{DateTime, Interval};
import scala.xml._

import thesmith.dotinfo.model.Info

/** Trait defines interface to retireve status information from Info services */
trait InfoService {
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
