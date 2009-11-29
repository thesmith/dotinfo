package thesmith.dotinfo.service.twitter

import java.net._
import java.util.Locale
import scala.xml._

import org.joda.time.format.{DateTimeFormat, DateTimeFormatter};

import thesmith.dotinfo.model.Info

/** Twitter Service defines interface to Twitter */
class TwitterService extends InfoService {
  private val formater = DateTimeFormat
      .forPattern("EEE MMM dd HH:mm:ss Z yyyy")
      .withLocale(Locale.US);
  private val domain = "http://twitter.com"
  
  /**
   * Returns populated Info object
   * "<li>"+createdAt+" <a href='http://twitter.com/"+name+
   * " rel='me'>I</a> <a href='http://twitter.com/"+name+
   * "/status/"+id+"'>tweeted</a>: '"+title+"'</li>"
   */
  def status(name: String): Info = {
    val xml = getXML("http://twitter.com/users/show.xml?screen_name=" + name)
    val createdAt = ago(formater.parseDateTime((xml\"status"\"created_at").text))
    val titleUrl = "http://twitter.com/"+name+"/status/"+(xml\"status"\"id").text
    val title = (xml\"status"\"text").text
    val userUrl = "http://twitter.com/"+name
    
    val info = new Info()
    info.title = title
    info.titleUrl = titleUrl
    info.userUrl = userUrl
    info.domain = domain
    info.ago = formater.parseDateTime((xml\"status"\"created_at").text).getMillis
    info
  }
}
