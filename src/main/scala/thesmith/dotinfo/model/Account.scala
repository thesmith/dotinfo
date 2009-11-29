package thesmith.dotinfo.model

import scala.reflect._
import javax.persistence._

/** Entity class describes an Account, the kind used by SGNodeMapper */
@Entity
@Table{ val name="account" }
class Account {
  @Id
  @GeneratedValue{ val strategy=GenerationType.IDENTITY }
  var id : Int = _

  @Version
  var version : Int = _

  @Column{ val name="person_id" }
  @BeanProperty var personId : String = _

  @Column{ val name="user_id" }
  @BeanProperty var userId : String = _

  @Column{ val name="username" }
  @BeanProperty var username : String = _

  @Column{ val name="domain" }
  @BeanProperty var domain : String = _
}
