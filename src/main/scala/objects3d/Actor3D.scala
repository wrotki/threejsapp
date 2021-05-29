package objects3d

import threejs.{Mesh, Object3D, Vector3}

class Actor3D(val mesh: Mesh) extends Object3D with Update {

  var components = Seq.empty[Component]

  def update(): Unit = {
    for (c <- components)
      c.update()
  }
}
