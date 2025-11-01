package fhw;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.smallrye.mutiny.Multi;

@Path("/sse")
public class sse
{

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Path("/stream")
    public Multi<String> streamRandomNumbers()
    {
        return Multi
                .createFrom()
                .ticks()
                .every(java.time.Duration.ofSeconds(1))
                .select()
                .first(7)
                .onOverflow()
                .drop()
                .map(n -> "tick " + n + " @ " + java.time.Instant.now())
                .onCompletion().invoke(() -> System.out.println("Finished!"))
                .onCancellation()
                .invoke(() -> System.out.println("Canceled!"));
    }
}
