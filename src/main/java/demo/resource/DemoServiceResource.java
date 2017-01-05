package demo.resource;

import com.codahale.metrics.annotation.Timed;
import demo.dao.ApplicationDAO;
import demo.dto.CreateApplicationDTO;
import demo.model.Application;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/application")
public class DemoServiceResource {

    private ApplicationDAO applicationDAO;

    public DemoServiceResource(ApplicationDAO applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public List<Application> getApplications() {
        return this.applicationDAO.getAll();
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Long createApplication(@Valid @NotNull CreateApplicationDTO createApplicationDTO) {
        return this.applicationDAO.insert(createApplicationDTO.getFullName());
    }
}
