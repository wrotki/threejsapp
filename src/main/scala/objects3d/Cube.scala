package objects3d

import threejs.{BoxGeometry, Mesh, MeshBasicMaterial, MeshStandardMaterial, Object3D}

object Cube {

  def apply(): Mesh = {
    val geometry = new BoxGeometry(1,1,1,1,1,1)
    val material = new MeshStandardMaterial( threejs.MeshStandardMaterialParameters(color = "#433F81") )
    new Mesh( geometry, material )
  }
}
