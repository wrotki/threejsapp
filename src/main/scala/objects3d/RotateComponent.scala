package objects3d

import scala.scalajs.js.Date

class RotateComponent(owner: Actor3D) extends Component(owner) with Update {

  private var lastUpdateTime = Date.now/1000.0

  def update(): Unit = {
    val oldPosition = owner.mesh.position
    val thisUpdateTime = Date.now/1000.0
    owner.mesh.rotation.x = (owner.rotation.x + thisUpdateTime - lastUpdateTime).toFloat
    owner.mesh.rotation.y = (owner.rotation.y + thisUpdateTime - lastUpdateTime).toFloat
    lastUpdateTime = thisUpdateTime
  }
}
