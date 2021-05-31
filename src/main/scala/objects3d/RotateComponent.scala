package objects3d

import threejs.{Euler, Vector3}

import scala.scalajs.js.{Date, JSON}

class RotateComponent(var owner: Actor3D) extends Component(owner) with Update {

  private var lastUpdateTime = Date.now/1000.0

  def update(): Unit = {
    val o: Actor3D = owner
    val thisUpdateTime = Date.now/1000.0
    println(s"thisUpdateTime: $thisUpdateTime")
//    val diff = thisUpdateTime - lastUpdateTime
    val id = owner.quaternion.identity().clone()
//    println(s"id: ${JSON.stringify(id)}")
//    val updateQuaternion = id.setFromAxisAngle(new Vector3(0f,1f,0f), (Math.PI*diff).toFloat)
    val updateQuaternion = id.setFromAxisAngle(new Vector3(0f,1f,0f), (Math.PI*thisUpdateTime).toFloat)
    println(s"updateQuaternion: ${JSON.stringify(updateQuaternion)}")
//    owner.quaternion.multiply(updateQuaternion)
//    owner.quaternion.normalize()
    owner.quaternion.copy(updateQuaternion)
    println(s"owner.quaternion: ${JSON.stringify(owner.quaternion)}")
    println(s"owner.rotation = ${JSON.stringify(o.rotation)}")
//    owner.updateMatrixWorld(true)
    lastUpdateTime = thisUpdateTime
  }
}
