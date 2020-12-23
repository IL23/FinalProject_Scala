import java.sql.{DriverManager, Statement}

import scala.io.StdIn._

object Main {
  def main(args: Array[String]): Unit = {
    val url = "jdbc:mysql://localhost:3307/chuckjokes"
    val username = "root"
    val password = "test123"
    val connection = DriverManager.getConnection(url, username, password)
    val statement: Statement = connection.createStatement

    JokeTable.createJokeTable(statement, connection)
    CategoryTable.createCategoryTable(statement, connection)

    println("Uz kādu vārdu nomainīt 'Chuck Norris'?")
    println("Vārds: ")
    val myName = readLine()
    println("Uzvārds: ")
    val lastName = readLine()
    JokeTable.changeName(myName, lastName, statement)

    JokeTable.countWords(statement)
    JokeTable.findTop5 (statement)
    CategoryTable.mostUsed
    connection.close()
  }
}