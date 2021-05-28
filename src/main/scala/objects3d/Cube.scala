package objects3d

import threejs.{BoxGeometry, Color, Mesh, MeshBasicMaterial, MeshStandardMaterial, Object3D, Vector3}

class Cube(mesh: Mesh) extends Actor3D(mesh) {
  override def trajectory(time: Double): Vector3 = {
    val x = startPosition.x + (2 * Math.sin(time))
    val y = startPosition.y + (2 * Math.cos(time))
    val z = startPosition.z + (2 * Math.cos(time))
    new Vector3(x.toFloat, y.toFloat, z.toFloat)
  }
}

object Cube {

  def apply(): Actor3D = {
    val geometry = new BoxGeometry(1,1,1,1,1,1)
    val material = new MeshStandardMaterial( threejs.MeshStandardMaterialParameters(color = "#433F81") )
    material.metalness = 0.8f
    material.roughness = 0.3f
    material.emissive = new Color("#3f4f3f")
    new Cube(new Mesh( geometry, material))
  }

}
