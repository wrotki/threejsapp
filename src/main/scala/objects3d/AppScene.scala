package objects3d

import org.scalajs.dom.{document, window}
import threejs.{DirectionalLight, FlyControls, Object3D, Vector3}

class AppScene()  {
}

object AppScene {

  val scene = new threejs.Scene
  println(s"$scene")
  val renderer = new threejs.WebGLRenderer(threejs.WebGLRendererParameters(antialias = true))
  val camera = new threejs.PerspectiveCamera(75f, (window.innerWidth/window.innerHeight).toFloat, 0.1f, 1000f)
  val flyControls = new FlyControls(camera, renderer.domElement)

  val cube = Cube(-5, 0, -500) // TODO figure out how to set up lights which need to look at some object
  scene.add(cube)

  // Lights
  val light = DirectionalLight(0xffffff, 2.0f, cube, 0, 0, -1000)
  val light2 = DirectionalLight(0xffffff, 2.0f, cube, 0, 0, 1000)
  val light3 = DirectionalLight(0xffffff, 2.0f, cube, -1000, 0, 1000)
  val light4 = DirectionalLight(0xffffff, 2.0f, cube, 1000, 0, 1000)

  def apply(): Unit = {
    renderer.setClearColor("#111111", 1.0f)
    renderer.setSize( window.innerWidth.toFloat, window.innerHeight.toFloat)
    document.body.appendChild( renderer.domElement )

    camera.position.z = 10

    flyControls.dragToLook = true
    flyControls.movementSpeed = 0.01f
    flyControls.rollSpeed = 0.001f

    scene.add(light)
    scene.add(light2)
    scene.add(light3)
    scene.add(light4)
  }

  def render(): Unit = {
    val lt = System.currentTimeMillis()
    lazy val renderLoop: (Double) => _ = (_: Double) => {
      window.requestAnimationFrame(renderLoop)

      cube.update()
//      cube2.update()
//      cube3.update()

      val now = System.currentTimeMillis()
      val secs = (now - lt) / 1000

      flyControls.update(1 * secs)
      renderer.render(scene, camera)
    }
    renderLoop(0.1)
  }
}
