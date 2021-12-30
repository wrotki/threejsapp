import scala.scalajs.js
import org.scalajs.dom
import dom.{console, document, window}
import objects3d.Cube
import org.scalajs.dom.raw.HTMLElement
import threejs.{Color, DirectionalLight, FlyControls, GLTF, GLTFLoader, MeshBasicMaterial, MeshBasicMaterialParameters, MeshStandardMaterial, Object3D}
import widgets.charts.Chart

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
    //    Look down for flycontrols.update call

    val htmlElement = document
    htmlElement.onkeydown = {(e: dom.KeyboardEvent) =>
//      println(s"addChartBlock: x: $x y: $y value: ${chartValue}")
      println(s"onkeydown: ${e.keyCode.toInt}")
      if( e.keyCode.toInt == 48  ) { // '0'
//        camera.position.x = 0
//        camera.position.y = 0
        camera.translateZ(-5)
      }
      if( e.keyCode.toInt == 57  ) { // '9'
//        camera.position.x = 0
//        camera.position.y = 0
        camera.translateX(-camera.position.x) // Back to 0
        camera.translateY(-camera.position.y) // Back to 0
        camera.translateZ(-camera.position.z) // Back to 0
      }
    }

    val scene = new threejs.Scene

    val cube = Cube(-5, 0, -500)
    scene.add(cube)

    val cube2 = Cube(5, 0, -500)
    println(JSON.stringify(cube2.material))
    cube2.material.asInstanceOf[MeshStandardMaterial].color = new Color("#1f6f2f")
    scene.add(cube2)

    val cube3 = Cube(0, 5, -500)
    cube3.material.asInstanceOf[MeshStandardMaterial].color = new Color("#6f1010")
    scene.add(cube3)

    val chart = Chart(2, 2, 0, 0, -5)
    scene.add(chart)
//    var data: Array[Array[Int]] = Array.ofDim(2, 8)
//    data(0)(0) = 1
//    data(0)(1) = 2
//    data(0)(2) = 4
//    data(0)(3) = 8
//    data(0)(4) = 16
//    data(0)(5) = 32
//    data(0)(6) = 64
//    data(0)(7) = 128
    var data: Array[Array[Int]] = prepareData()
    chart.setData(data)

    // Lights
    val light = DirectionalLight(0xffffff, 2.0f, cube, 0, 0, -1000)
    scene.add(light)

    val light2 = DirectionalLight(0xffffff, 2.0f, chart, 0, 0, 1000)
    scene.add(light2)

    val light3 = DirectionalLight(0xffffff, 2.0f, chart, -1000, 0, 1000)
    scene.add(light3)

    val light4 = DirectionalLight(0xffffff, 2.0f, chart, 1000, 0, 1000)
    scene.add(light4)


    val loader = new GLTFLoader
    loader.load("3d/missile.glb",
      (gltf) => {
        gltf.asInstanceOf[GLTF].scene.position.z = gltf.asInstanceOf[GLTF].scene.position.z - 500
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

  private def prepareData(): Array[Array[Int]] = {
    val dataSize = 16
    var data: Array[Array[Int]] = Array.ofDim(dataSize, dataSize)
    for(x <- data.indices)
      for(y <- data(x).indices) {
        val v = Math.pow(x - dataSize/2, 2) + Math.pow(y - dataSize/2, 2)
        println(s"value: x: $x y: $y v: $v")
        data(x)(y) = (10.0 * Math.exp(-v)).toInt
      }
    data
  }
}
