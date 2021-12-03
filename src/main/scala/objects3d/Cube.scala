package objects3d

import threejs.{BoxGeometry, BufferGeometry, Color, Material, Mesh, MeshBasicMaterial, MeshStandardMaterial, Object3D, Vector3}

class Cube(geometry: BufferGeometry, material: Material) extends Mesh(geometry, material) with Update {
  var components = Seq.empty[Component]

  def update: Unit = {
    for (c <- components)
      c.update()
  }
}

object Cube {

  def apply(): Cube = {
    val geometry = new BoxGeometry(1,1,1,1,1,1)
    val material = new MeshStandardMaterial( threejs.MeshStandardMaterialParameters(color = "#433F81") )
    material.metalness = 0.8f
    material.roughness = 0.3f
    material.emissive = new Color("#1f4fef")
    val ret = new Cube( geometry, material)
    ret.components = ret.components :+ new MoveInCircleComponent(ret) :+ new RotateComponent(ret)
    ret
  }

  def apply(x: Float, y: Float, z: Float): Cube = {
    val ret = Cube()
    ret.position.x = x
    ret.position.y = y
    ret.position.z = z
    ret
  }
}
