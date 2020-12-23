import java.sql.{Connection, PreparedStatement, Statement}

import scala.collection.mutable.Map

object CategoryTable {
  val tableCat: Array [JokesData] = Parser.data

  def createCategoryTable(statement: Statement, connection: Connection): Unit = {

    statement.execute(
      """CREATE TABLE IF NOT EXISTS `categories`(
        |category CHAR(50),
        |id INT
        |);""".stripMargin
    )
    statement.execute("DELETE FROM categories;")

    val queryString: String = "INSERT INTO `categories` (category,id) VALUES (?,?);"
    val preparedStatement: PreparedStatement = connection.prepareStatement(queryString)

    connection.setAutoCommit(false)

    for (i <- 0 until tableCat.length) {
      val category: JokesData = tableCat(i)
      for (j <-0 until category.categories.length) {
        preparedStatement.setString(1, category.categories(j))
        preparedStatement.setInt(2, category.id)
        preparedStatement.addBatch()
      }
    }

    val updateCounts = preparedStatement.executeBatch()
    println(updateCounts.sum + " rindas ievadītas tabulā 'Categories'")
    connection.commit()
    connection.setAutoCommit(true)
  }

  def mostUsed = {
    val catTypeMap: Map[String, Int] = Map()
    for (i <- 0 until tableCat.length) {
      val category: JokesData = tableCat(i)
      for (j <-0 until category.categories.length) {
        if(catTypeMap.contains(category.categories(j))) {
          catTypeMap.put(category.categories(j), catTypeMap(category.categories(j))+1)
        }
        else {
          catTypeMap.put(category.categories(j), 1)
        }
      }
    }
    // println("Populārākā kategorija ir: " + catTypeMap.max)
    println("Populārākā kategorija ir: " + catTypeMap.keysIterator.max)
  }
}
