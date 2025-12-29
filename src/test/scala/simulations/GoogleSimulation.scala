package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class GoogleSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://www.google.com")

  val scn = scenario("Google Home Page Test")
    .exec(
      http("Open Google")
        .get("/")
        .check(status.is(200))
    )

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)
}
