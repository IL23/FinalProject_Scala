import io.circe.generic.auto._
import io.circe.parser._

case class AllData(
                   // typeType: String,
                    value: Array[JokesData])
case class JokesData(id: Int, joke: String, categories: Array[String])

object Parser {
  val data = parse()

  def parse(): Array [JokesData] = {
    val input = HttpData.getData()
      //.replace("type", "typeType")

  val decodeResult = decode[AllData](input)

    val result = decodeResult match {
      case Right(staff) => staff
      case Left(error) => throw new Exception(error.getMessage)
    }

  // println(result.value(0).joke) // value ir masīvs, kuram var iet cauri un rakstīt tabulā
    result.value
  }


}