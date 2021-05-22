import org.scalajs.dom
import dom.{console, document, window}

object MainApp {
  def main(args: Array[String]): Unit = {
    MainAppImpl.main
  }
}

object MainAppImpl  {

  def main(): Unit = {
    println("Starting 'threejsapp'...")

    val scene = new threejs.Scene

    val camera = new threejs.PerspectiveCamera(75f, (window.innerWidth/window.innerHeight).toFloat, 0.1f, 1000f)
    camera.position.z = 4

    val renderer = new threejs.WebGLRenderer(threejs.WebGLRendererParameters(antialias = true))
    renderer.setClearColor("#00ff00", 1.0f)
    renderer.setSize( window.innerWidth.toFloat, window.innerHeight.toFloat)
    document.body.appendChild( renderer.domElement )

    val geometry = new threejs.BoxGeometry(1,1,1,1,1,1)
    val material = new threejs.MeshBasicMaterial( threejs.MeshBasicMaterialParameters(color = "#433F81") )
    val cube = new threejs.Mesh( geometry, material )
    scene.add(cube)

    lazy val render: (Double) => _ = (_: Double) => {
      window.requestAnimationFrame( render )

      cube.rotation.x = cube.rotation.x + 0.04f
      cube.rotation.y = cube.rotation.y + 0.04f

      renderer.render(scene, camera)
    }

    val p = document.createElement("p")
    val text = document.createTextNode("Hello!")
    p.appendChild(text)
    document.body.appendChild(p)

    render(0.1)
  }
}
