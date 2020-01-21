/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dto.MovieInfoDTO;
import facades.MovieInfoFacade;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Henrik
 */
@Path("movieInfo")
public class MovieInfoResource {
    
    private static final MovieInfoFacade FACADE =  MovieInfoFacade.getMovieInfoFacade();

    @Context
    private UriInfo context; 

    
    /**
     * Creates a new instance of SwappiResource
     */
    public MovieInfoResource() {
    }

    /**
     * Retrieves representation of an instance of rest.SwappiResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieInfoDTO getJson(@PathParam("title") String title) throws Exception {
        return FACADE.getMovieInfo(title);
    }
    
    @GET
    @Path("all/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public MovieInfoDTO getJsonAll(@PathParam("title") String title) throws Exception {
        return FACADE.getMovieInfoAll(title);
    }
}
