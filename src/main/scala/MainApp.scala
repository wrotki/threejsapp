import scala.scalajs.js
import org.scalajs.dom
import dom.{console, document, window}
import threejs.{Color, DirectionalLight, MeshBasicMaterial, MeshBasicMaterialParameters, MeshStandardMaterial}

import scala.scalajs.js.{Date, JSON}
import scala.scalajs.js.annotation.JSExportTopLevel

object MainApp {
  def main(args: Array[String]): Unit = {
    MainAppImpl.main
  }
}

object Globals {
  @JSExportTopLevel("cube")
  var cube: js.Object = null
}

object MainAppImpl  {

  def main(): Unit = {
    println("Starting 'threejsapp'...")

    val camera = new threejs.PerspectiveCamera(75f, (window.innerWidth/window.innerHeight).toFloat, 0.1f, 1000f)
    camera.position.z = 5

    val renderer = new threejs.WebGLRenderer(threejs.WebGLRendererParameters(antialias = true))
    renderer.setClearColor("#111111", 1.0f)
    renderer.setSize( window.innerWidth.toFloat, window.innerHeight.toFloat)
    document.body.appendChild( renderer.domElement )

    val scene = new threejs.Scene

    val cube = objects3d.Cube()
    scene.add(cube.mesh)

    val cube2 = objects3d.Cube()
    Globals.cube = cube2.mesh
    println(JSON.stringify(cube2.mesh.material))
    cube2.mesh.position.x = -2
    cube2.mesh.material.asInstanceOf[MeshStandardMaterial].color = new Color("#1f6f2f")
    scene.add(cube2.mesh)

    val cube3 = objects3d.Cube()
    cube3.mesh.position.x = 2
    cube3.mesh.material.asInstanceOf[MeshStandardMaterial].color = new Color("#6f1010")
    scene.add(cube3.mesh)

    val light = new DirectionalLight(0xffffff, 2.0f)
    light.position.x = -10
    light.position.y = 5
    light.position.z = 10
    light.target = cube3.mesh
    scene.add(light)
    val light2 = new DirectionalLight(0xffffff, 2.0f)
    light2.position.x = 10
    light2.position.y = 5
    light2.position.z = 10
    light2.target = cube.mesh
    scene.add(light2)
    val light3 = new DirectionalLight(0xffffff, 2.0f)
    light3.position.x = 0
    light3.position.y = -5
    light3.position.z = 10
    light3.target = cube.mesh
    scene.add(light3)

    lazy val render: (Double) => _ = (_: Double) => {
      window.requestAnimationFrame( render )

      cube.rotation.x = cube.rotation.x + 0.04f
      cube.rotation.y = cube.rotation.y + 0.04f
      cube2.rotation.x = cube2.rotation.x + 0.04f
      cube2.rotation.y = cube2.rotation.y + 0.04f
      cube3.rotation.x = cube3.rotation.x + 0.04f
      cube3.rotation.y = cube3.rotation.y + 0.04f

      cube.update()
      cube2.update()
      cube3.update()

      renderer.render(scene, camera)
    }

    val p = document.createElement("p")
    val text = document.createTextNode("Hello!")
    p.appendChild(text)
    document.body.appendChild(p)

    render(0.1)
  }
}
