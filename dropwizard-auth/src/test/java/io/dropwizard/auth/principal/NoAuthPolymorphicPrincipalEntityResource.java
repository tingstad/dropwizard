package io.dropwizard.auth.principal;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Contains resource methods which don't authenticate but use
 * multi-principal injection and thus might be affected by
 * authentication logic.
 */
@Path("/no-auth-test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public class NoAuthPolymorphicPrincipalEntityResource {

    /**
     * Principal instance must be injected even when no authentication is required.
     */
    @POST
    @Path("json-principal-entity")
    public String principalEntityWithoutAuth(JsonPrincipal principal) {
        assertThat(principal).isNotNull();
        return principal.getName();
    }

    /**
     * Principal instance must be injected even when no authentication is required.
     */
    @POST
    @Path("null-principal-entity")
    public String principalEntityWithoutAuth(NullPrincipal principal) {
        assertThat(principal).isNotNull();
        return principal.getName();
    }

    /**
     * Annotated principal instance must be injected even when no authentication is required.
     */
    @POST
    @Path("annotated-json-principal-entity")
    public String annotatedPrincipalEntityWithoutAuth(@DummyAnnotation JsonPrincipal principal) {
        assertThat(principal).isNotNull();
        return principal.getName();
    }

    /**
     * Annotated principal instance must be injected even when no authentication is required.
     */
    @POST
    @Path("annotated-null-principal-entity")
    public String annotatedPrincipalEntityWithoutAuth(@DummyAnnotation NullPrincipal principal) {
        assertThat(principal).isNotNull();
        return principal.getName();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.PARAMETER })
    public @interface DummyAnnotation {
    }
}
