package thesmith.dotinfo.model

class Account (id: String, name: String, dom: String) {
  var userId = id
  var username = name
  var domain = dom
  
  override def toString(): String = userId+", "+username+", "+domain
}
