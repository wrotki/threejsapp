package objects3d

import threejs.{Object3D, BoxGeometry, Mesh, MeshBasicMaterial}

object Cube {

  def apply(): Object3D = {
    val geometry = new BoxGeometry(1,1,1,1,1,1)
    val material = new MeshBasicMaterial( threejs.MeshBasicMaterialParameters(color = "#433F81") )
    new Mesh( geometry, material )
  }
}
