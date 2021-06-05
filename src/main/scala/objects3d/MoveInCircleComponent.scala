package objects3d

import threejs.Mesh

import scala.scalajs.js.Date

class MoveInCircleComponent(owner: Mesh) extends Component(owner) with Update {

  private var lastUpdateTime = Date.now/1000.0

  def update(): Unit = {
    val oldPosition = owner.position
    val thisUpdateTime = Date.now/1000.0
    val newPosX = oldPosition.x + (1 * (Math.sin(thisUpdateTime) - Math.sin(lastUpdateTime))).toFloat
    val newPosY = oldPosition.y + (1 * (Math.cos(thisUpdateTime) - Math.cos(lastUpdateTime))).toFloat
    val newPosZ = oldPosition.z + (1 * (Math.cos(thisUpdateTime) - Math.cos(lastUpdateTime))).toFloat

    owner.position.x = newPosX.toFloat
    owner.position.y = newPosY.toFloat
    owner.position.z = newPosZ.toFloat
//    owner.mesh.updateMatrixWorld(true)

    lastUpdateTime = thisUpdateTime
  }
}
