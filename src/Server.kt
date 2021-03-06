import com.baidyanathprasad.jxrs.Application
import jakarta.ws.rs.core.UriBuilder
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory

fun main(args: Array<String>) {
    val url = UriBuilder.fromUri("http://0.0.0.0/")
        .port(8080)
        .build()

    val httpServer = GrizzlyHttpServerFactory.createHttpServer(
        url,
        Application(),
        true
    )

    if (System.getenv()["SHUTDOWN_TYPE"].equals("INPUT")) {
        println("Press any key to shutdown")
        readLine()
        println("Shutting down from input")
        httpServer.shutdownNow()
    } else {
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("Shutting down from shutdown hook")
                httpServer.shutdownNow()
            }
        )

        println("Press Ctrl+C to shutdown")
        Thread.currentThread().join()
    }
}
