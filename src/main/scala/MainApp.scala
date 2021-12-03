import scala.scalajs.js
import org.scalajs.dom
import dom.{console, document, window}
import objects3d.Cube
import threejs.{Color, DirectionalLight, GLTF, GLTFLoader,
  MeshBasicMaterial, MeshBasicMaterialParameters, MeshStandardMaterial, Object3D,
  FlyControls
}

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
    camera.position.z = 10

    val renderer = new threejs.WebGLRenderer(threejs.WebGLRendererParameters(antialias = true))
    renderer.setClearColor("#111111", 1.0f)
    renderer.setSize( window.innerWidth.toFloat, window.innerHeight.toFloat)
    document.body.appendChild( renderer.domElement )

    val flyControls = new FlyControls(camera, renderer.domElement)
    flyControls.dragToLook = true
    flyControls.movementSpeed = 0.01f
    flyControls.rollSpeed = 0.001f

    val scene = new threejs.Scene

    val cube = Cube(-5, 0, 0)
    scene.add(cube)

    val cube2 = Cube(5, 0, 0)
    println(JSON.stringify(cube2.material))
    cube2.material.asInstanceOf[MeshStandardMaterial].color = new Color("#1f6f2f")
    scene.add(cube2)

    val cube3 = Cube(0, 5, 0)
    cube3.material.asInstanceOf[MeshStandardMaterial].color = new Color("#6f1010")
    scene.add(cube3)

    val light = DirectionalLight(0xffffff, 2.0f, cube3, -10, 5, 10)
    scene.add(light)

    val light2 = DirectionalLight(0xffffff, 2.0f, cube, 0, -5, 10)
    scene.add(light2)

    val light3 = DirectionalLight(0xffffff, 2.0f, cube, 0, -5, 10)
    scene.add(light3)

    val loader = new GLTFLoader
    loader.load("3d/missile.glb",
      (gltf) => {
        scene.add(gltf.asInstanceOf[GLTF].scene)
        Globals.cube = gltf.asInstanceOf[GLTF].scene
      },
      (progress) => {} ,
      (error) => {
        println(("Error loading GLTF: $error"))
      }
    )

    val lt = System.currentTimeMillis()

    lazy val render: (Double) => _ = (_: Double) => {
      window.requestAnimationFrame( render )

//      cube.rotation.x = cube.rotation.x + 0.04f
//      cube.rotation.y = cube.rotation.y + 0.04f
//      cube2.rotation.x = cube2.rotation.x + 0.04f
//      cube2.rotation.y = cube2.rotation.y + 0.04f
//      cube3.rotation.x = cube3.rotation.x + 0.04f
//      cube3.rotation.y = cube3.rotation.y + 0.04f
//      println(s"From main: ${JSON.stringify(cube)}")

      cube.update()
      cube2.update()
      cube3.update()

      val now = System.currentTimeMillis()

      val secs = (now - lt) / 1000

      flyControls.update(1 * secs)
      renderer.render(scene, camera)
    }

    val p = document.createElement("p")
    val text = document.createTextNode("Hello!")
    p.appendChild(text)
    document.body.appendChild(p)

    render(0.1)
  }
}
