import java.sql.{Connection, PreparedStatement, Statement}

import scala.collection.mutable.Map

object JokeTable {
  val jokes: Array [JokesData] = Parser.data
  var joke: JokesData = jokes(0)

  def createJokeTable(statement: Statement, connection: Connection): Unit = {

    statement.execute(
      """CREATE TABLE IF NOT EXISTS `jokes`(
        |num INT,
        |id INT,
        |joke VARCHAR(500)
        |);
        """.stripMargin
    )
    statement.execute(      "DELETE FROM jokes ;")

    val queryString: String = "INSERT INTO `jokes` (num,id,joke) VALUES (?,?,?);"
    val preparedStatement: PreparedStatement = connection.prepareStatement(queryString)

    connection.setAutoCommit(false)

    for (i <- 0 until jokes.length) {
      joke = jokes(i)
      preparedStatement.setInt(1, i+1)
      preparedStatement.setInt(2, joke.id)
      preparedStatement.setString(3, joke.joke)
      preparedStatement.addBatch()

    }
    val updateCounts = preparedStatement.executeBatch()
    println(updateCounts.sum + " rindas ievadītas tabulā 'Jokes'")
    connection.commit()
    connection.setAutoCommit(true)
  }

  def changeName(newName: String, newLastName: String, statement: Statement): Unit = {
    statement.execute( "UPDATE `jokes` SET joke = REPLACE (REPLACE (joke, 'Chuck',  '"+ newName +"' ), 'Norris', '"+ newLastName +"' );" )
   }


  def countWords (statement: Statement) = {
    var count: Double = 0
    val jokesWords = statement.executeQuery("SELECT joke FROM jokes;")
    while (jokesWords.next){
      val words: Array [String] = jokesWords.getString("joke").split(" ")
      count = count + words.length
    }
    println("Vidējais vārdu skaits: " + count/jokes.length)
  }

  def findTop5 (statement: Statement) = {

    val jokesWords = statement.executeQuery("SELECT joke FROM jokes;")
    val wordCountMap: Map[String, Int] = Map()
    while (jokesWords.next){
      val words: Array [String] = jokesWords.getString("joke").toLowerCase()
        .replace(",", "")
        .replace("(", "")
        .replace(")", "")
        .replace(".", "")
        .replace("#", "")
        .replace("'&quot;", "")
        .split(" ")
      for (word: String <- words) {
        if(wordCountMap.contains(word)) {
          val count: Int = wordCountMap(word)
          wordCountMap.put(word, count+1)
        }
        else {
          wordCountMap.put(word, 1)
        }
      }
    }
    for (i <- 1 to 5) {
      val max: (String, Int) = wordCountMap.maxBy(_._2)
      println("Top " + i + " biežāk lietotais vārds ir: " + max)
      wordCountMap.remove(max._1)
    }
  }
}