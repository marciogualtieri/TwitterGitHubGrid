[buhtig (GitHub REST API Client)](https://github.com/mdread/buhtig)
[json4s](https://github.com/json4s/json4s)
[GitHub REST API Repository Search](https://developer.github.com/v3/search/#search-repositories)
[twitter4j](http://twitter4j.org/)
[Twitter REST API Search](https://dev.twitter.com/rest/public/search)
[scopt (Simple Scala Command-line Options Parsing)](https://github.com/scopt/scopt)
[scalamock](http://scalamock.org)
[sbt-coverage](https://github.com/scoverage/sbt-scoverage)

    sbt clean test
    
    sbt clean it:test
    
    sbt clean coverage test
    sbt coverageReport
    
    sbt clean coverage it:test
    sbt coverageReport

    sbt clean assembly
    
    java -Dconfig.file=application.conf \
         -jar ./target/scala-2.11/grid-assembly-1.0.jar reactive -o output.json

    java -Dconfig.file=application.conf \
         -Dlog4j.logLevel=DEBUG \
         -jar ./target/scala-2.11/grid-assembly-1.0.jar reactive -o output.json