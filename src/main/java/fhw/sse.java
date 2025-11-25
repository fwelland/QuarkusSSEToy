package fhw;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.smallrye.mutiny.Multi;
import org.jboss.resteasy.reactive.RestStreamElementType;

@Path("/sse")
public class sse {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Path("/stream")
    public Multi<String> stream() {
        return Multi
                .createFrom()
                .ticks()
                .every(java.time.Duration.ofSeconds(1))
                .select()
                .first(7)
                .onOverflow()
                .drop()
                .map(n -> "<li>tick " + n + " @ " + java.time.Instant.now() + "</li>")
                .onCompletion().invoke(() -> System.out.println("Finished!"))
                .onCancellation()
                .invoke(() -> System.out.println("Canceled!"));
    }

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance info(Info item);
    }

    @GET
    //@Produces(MediaType.SERVER_SENT_EVENTS_TYPE)
    @RestStreamElementType(MediaType.TEXT_HTML)
    @Path("/stream1")
    public Multi<String> stream1() {
        return Multi
                .createFrom()
                .ticks()
                .every(java.time.Duration.ofSeconds(3))
                .select()
                .first(7)
                .onOverflow()
                .drop()
                .map(n -> Templates.info(new Info("iteration " + n, java.time.Instant.now().toString())).render() )
                .onCompletion().invoke(() -> System.out.println("Finished!"))
                .onCancellation()
                .invoke(() -> System.out.println("Canceled!"));
    }

}
