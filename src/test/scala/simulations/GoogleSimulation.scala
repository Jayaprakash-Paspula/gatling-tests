package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class GoogleSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://www.google.com")
    .acceptHeader("text/html")
    .userAgentHeader("Gatling Load Test")

  val scn = scenario("Google Home Page Load Test")
    .exec(
      http("Open Google Home Page")
        .get("/")
        .check(status.is(200))
    )
    .pause(2)

  setUp(
    scn.inject(
      rampUsers(10).during(10.seconds)
    )
  ).protocols(httpProtocol)
}
