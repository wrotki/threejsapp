package objects3d

import threejs.{Mesh, Object3D, Vector3}

class Actor3D(val mesh: Mesh) extends Object3D {

  val startPosition = new Vector3(mesh.position.x,mesh.position.y,mesh.position.z)
//  println(s"Mesh start position: ${startPosition.x},${startPosition.y},${startPosition.z} ")

  def trajectory(time: Double): Vector3 = { // TODO replace with lambda, so trajectories become changeable over time
    startPosition // Default - position unchanged
  }

  def move(time: Double): Unit = {
//    println(f"Time: ${time}%f ")
    val newPosition = trajectory(time)
//    println(s"New position: ${newPosition.x},${newPosition.y},${newPosition.z}}")
    mesh.position.x = newPosition.x
    mesh.position.y = newPosition.y
    mesh.position.z = newPosition.z
//    println(s"Mesh start position: ${startPosition.x},${startPosition.y},${startPosition.z} ")
//    println(s"Mesh new position: ${mesh.position.x},${mesh.position.y},${mesh.position.z} ")
  }
}
