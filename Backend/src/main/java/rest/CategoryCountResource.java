/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dto.JokesDTO;
import entities.Category;
import facades.RequestFacade;
import facades.SwappiFacade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author Henrik
 */
@Path("categoryCount")
public class CategoryCountResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/TESTexam2",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final RequestFacade FACADE =  RequestFacade.getRequestFacade(EMF);
    
    @Context
    private UriInfo context;


    /**
     * Creates a new instance of SwappiResource
     */
    public CategoryCountResource() {
    }

    /**
     * Retrieves representation of an instance of rest.SwappiResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{categories}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public String getJson(@PathParam("categories") String category) throws Exception {
        Category cat = FACADE.getCategory(category);
        
        return "{\"count\":" + cat.getRequests().size() + "}";
        
    }
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAll() throws Exception {
        return FACADE.getAllCategories();
    }
    
}
