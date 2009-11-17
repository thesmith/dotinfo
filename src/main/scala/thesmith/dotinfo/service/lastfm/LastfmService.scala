package thesmith.dotinfo.service.lastfm

import java.util.Locale
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter};

import thesmith.dotinfo.model.Info

/** Last.fm service retuns status from last fm */
class LastfmService extends InfoService {
  private val formater = DateTimeFormat
      .forPattern("EEE, dd MMM yyyy HH:mm:ss Z")
      .withLocale(Locale.ENGLISH);
  private val domain = "http://last.fm"
  
  /** 
   * Return Info populated Info object for last.fm
   * "As far as <a href='http://last.fm'>last.fm</a> knows, the last thing <a href='"+userLink+
   * "'>I</a> listened to was '<a href='"+link+"'>"+title+
   * "</a>. That was "+ago(formater.parseDateTime(pubDate))+"."
   */
  def status(name: String): Info = {
    val xml = getXML("http://ws.audioscrobbler.com/2.0/user/"+name+"/recenttracks.rss")
    val userLink = (xml\"channel"\"link").text
    val item = (xml\\"item")(0)
    
    val title = (item\"title").text
    val link = (item\"link").text
    val guid = (item\"guid").text
    val pubDate = (item\"pubDate").text
    
    return new Info(title, link, guid, domain, formater.parseDateTime(pubDate))
  }
}
