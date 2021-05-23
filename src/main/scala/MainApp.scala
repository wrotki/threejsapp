import scala.scalajs.js
import org.scalajs.dom
import dom.{console, document, window}
import threejs.{Color, MeshBasicMaterial, MeshBasicMaterialParameters}

import scala.scalajs.js.JSON
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

    val scene = new threejs.Scene

    val camera = new threejs.PerspectiveCamera(75f, (window.innerWidth/window.innerHeight).toFloat, 0.1f, 1000f)
    camera.position.z = 12

    val renderer = new threejs.WebGLRenderer(threejs.WebGLRendererParameters(antialias = true))
    renderer.setClearColor("#00ff00", 1.0f)
    renderer.setSize( window.innerWidth.toFloat, window.innerHeight.toFloat)
    document.body.appendChild( renderer.domElement )

    val cube = objects3d.Cube()
    scene.add(cube)

    val cube2 = objects3d.Cube()
    Globals.cube = cube2
    println(JSON.stringify(cube2.material))
    cube2.position.x = -2
    cube2.material.color = new Color("#1f6f2f")
    scene.add(cube2)

    val cube3 = objects3d.Cube()
    cube3.position.x = 2
    cube3.material.color = new Color("#6f1010")
    scene.add(cube3)

    lazy val render: (Double) => _ = (_: Double) => {
      window.requestAnimationFrame( render )

      cube.rotation.x = cube.rotation.x + 0.04f
      cube.rotation.y = cube.rotation.y + 0.04f
      cube2.rotation.x = cube2.rotation.x + 0.04f
      cube2.rotation.y = cube2.rotation.y + 0.04f
      cube3.rotation.x = cube3.rotation.x + 0.04f
      cube3.rotation.y = cube3.rotation.y + 0.04f

      renderer.render(scene, camera)
    }

    val p = document.createElement("p")
    val text = document.createTextNode("Hello!")
    p.appendChild(text)
    document.body.appendChild(p)

    render(0.1)
  }
}
