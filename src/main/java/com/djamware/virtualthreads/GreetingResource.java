package com.djamware.virtualthreads;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    ConcurrentService concurrentService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return concurrentService.runConcurrentTasks();
    }
}
