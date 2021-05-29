package objects3d

import scala.scalajs.js.Date

class MoveInCircleComponent(owner: Actor3D) extends Component(owner) with Update {

  private var lastUpdateTime = Date.now/1000.0

  def update(): Unit = {
    val oldPosition = owner.mesh.position
    val thisUpdateTime = Date.now/1000.0
    val newPosX = oldPosition.x + 2 * (Math.sin(thisUpdateTime) - Math.sin(lastUpdateTime))
    val newPosY = oldPosition.y + 2 * (Math.cos(thisUpdateTime) - Math.cos(lastUpdateTime))
    val newPosZ = oldPosition.z + 2 * (Math.cos(thisUpdateTime) - Math.cos(lastUpdateTime))

    owner.mesh.position.x = newPosX.toFloat
    owner.mesh.position.y = newPosY.toFloat
    owner.mesh.position.z = newPosZ.toFloat

    lastUpdateTime = thisUpdateTime
  }
}
