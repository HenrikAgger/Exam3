/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dto.JokesDTO;
import entities.Category;
import facades.FacadeExample;
import facades.RequestFacade;
import facades.SwappiFacade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
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
@Path("jokeByCategory")
public class SwappiResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/exam3",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final RequestFacade FACADE =  RequestFacade.getRequestFacade(EMF);

    @Context
    private UriInfo context; 

    
    /**
     * Creates a new instance of SwappiResource
     */
    public SwappiResource() {
    }

    /**
     * Retrieves representation of an instance of rest.SwappiResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{categories}")
    @Produces(MediaType.APPLICATION_JSON)
    public JokesDTO getJson(@PathParam("categories") String categories) throws Exception {
        SwappiFacade swappi = new SwappiFacade();

        List<Category> cats = new ArrayList();
        
        for(String cat : categories.split(",")) {
            try {
                Category category = FACADE.getCategory(cat);
                cats.add(category);
            } catch (NoResultException ex){
                throw new WebApplicationException("not a valid category"); 
            }
        }

        if(cats.size() > 4){
            throw new WebApplicationException("more than 4 categories"); 
        }

        
        FACADE.createRequest(cats);
        return swappi.getAll(cats);
    }
}
