# FinalProject
Build an application that queries data from Chuck Norris Jokes api http://www.icndb.com/api/.
Saves 200 jokes with respective categories (different table) to your own SQL or any other database.

When jokes are saved to the database the main characters name is changed to your name.

Prints out metrics about your database.
    - Average amount of words used in each joke.
    - Top 5 most used words in the jokes and how many times they were used.
    - Most popular category

What you will need to find on your own:
    - Class to handle HTTP requests to api.
        https://stackoverflow.com/questions/11719373/doing-http-request-in-scala
        Dependency:  "org.scalaj" %% "scalaj-http" % "2.4.2"
    - Class to parse the JSON response.
https://circe.github.io/circe/
Dependencies:
val circeVersion = “0.12.0”
          "io.circe"  %% "circe-core"     % circeVersion,
            "io.circe"  %% "circe-generic"  % circeVersion,
            "io.circe"  %% "circe-parser"   % circeVersion

Code structure requirements:
    - Configuration has to be in a separate file (database address, credentials)
    - Table for Jokes, categories, has to have a corresponding class in the code base.
    - A separate class or object has to do all of the interactions with the database (reading, writing, etc.)
    - A separate class or object has to do all of the interactions with the API (reading)
