package thesmith.dotinfo.model

import org.joda.time.DateTime

/** Info class represents individual status information */
class Info (tit: String, titUrl: String, usrUrl: String, domUrl: String, ag: DateTime) {
  var title = tit
  var titleUrl = titUrl
  var userUrl = usrUrl
  var domainUrl = domUrl
  var ago = ag
  
  override def toString(): String = title+", "+titleUrl+", "+userUrl+", "+domainUrl+", "+ago.toString()
}

