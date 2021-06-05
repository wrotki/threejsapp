package objects3d

import threejs.{Euler, Mesh, Quaternion, Vector3}

import scala.scalajs.js.{Date, JSON}

class RotateComponent(owner: Mesh) extends Component(owner) with Update {

  private var lastUpdateTime = Date.now/1000.0

  def update(): Unit = {
    val o = owner
    val thisUpdateTime = Date.now/1000.0
    val diff = thisUpdateTime - lastUpdateTime
//    println(s"diff: $diff")
    val id = new Quaternion(0f,0f,0f,0f).identity()
//    println(s"id: ${JSON.stringify(id)}")
    val updateQuaternion = id.setFromAxisAngle(new Vector3(0f,1f,0f), (Math.PI*diff).toFloat)
//    println(s"updateQuaternion: ${JSON.stringify(updateQuaternion)}")
    val newOwnerQuaternion = owner.quaternion.clone()
    newOwnerQuaternion.multiply(updateQuaternion)
    newOwnerQuaternion.normalize()
//    println(s"newOwnerQuaternion: ${JSON.stringify(newOwnerQuaternion)}")
//    println(s"owner.quaternion - old: ${JSON.stringify(owner.quaternion)}")
    owner.quaternion.copy(newOwnerQuaternion)
//    println(s"owner.quaternion - new: ${JSON.stringify(owner.quaternion)}")
//    println(s"owner.rotation = ${JSON.stringify(o.rotation)}")
    lastUpdateTime = thisUpdateTime
  }
}
