import org.scalajs.dom
import dom.{console, document, window}

import threejs.Scene

object MainApp {
  def main(args: Array[String]): Unit = {
    MainAppImpl.main
  }
}

object MainAppImpl  {

  def main(): Unit = {
    println("Starting 'threejsapp'...")

    val scene = new Scene
    //    js.Dynamic.global.scene = scene
    console.log(s"Scene: $scene")

    val p = document.createElement("p")
    val text = document.createTextNode("Hello!")
    p.appendChild(text)
    document.body.appendChild(p)
  }

}
