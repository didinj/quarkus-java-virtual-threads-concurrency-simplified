package com.djamware.virtualthreads;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/compare")
public class PerformanceResource {

    @Inject
    PerformanceService performanceService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String compare() {
        return performanceService.comparePerformance();
    }
}
