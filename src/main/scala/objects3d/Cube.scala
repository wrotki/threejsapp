package objects3d

import threejs.{BoxGeometry, Color, Mesh, MeshBasicMaterial, MeshStandardMaterial, Object3D, Vector3}

class Cube(mesh: Mesh) extends Actor3D(mesh) {
}

object Cube {

  def apply(): Actor3D = {
    val geometry = new BoxGeometry(1,1,1,1,1,1)
    val material = new MeshStandardMaterial( threejs.MeshStandardMaterialParameters(color = "#433F81") )
    material.metalness = 0.8f
    material.roughness = 0.3f
    material.emissive = new Color("#3f4f3f")
    val ret = new Cube(new Mesh( geometry, material))
    ret.components = ret.components :+ new MoveInCircleComponent(ret) :+ new RotateComponent(ret)
    ret
  }

}
