package thesmith.dotinfo.model

import org.scala_libs.jpa.LocalEMF
import net.liftweb.jpa.RequestVarEM
import scala.collection.jcl.{BufferWrapper,SetWrapper}

object Model extends LocalEMF("jpaweb") with RequestVarEM {
  implicit def setToWrapper[A](set : java.util.Set[A]) = new SetWrapper[A]{override def underlying = set}
  implicit def listToWrapper[A](list : java.util.List[A]) = new BufferWrapper[A]{override def underlying = list}
}  

