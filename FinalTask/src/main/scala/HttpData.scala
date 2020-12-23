import scalaj.http.Http

object HttpData {
  def getData(): String = {

    val response: String = Http("http://api.icndb.com/jokes/random/200").asString.body
    response
   // println(response)
  }
}