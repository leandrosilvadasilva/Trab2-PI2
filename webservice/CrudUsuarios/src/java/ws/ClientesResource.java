/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Cliente;
import negocio.ClienteNegocio;

@Path("clientes")
public class ClientesResource {

    @Context
    private UriInfo context;
    
    @EJB
    private ClienteNegocio clienteService;
    
    /**
     * Creates a new instance of UsuariosResource
     */
    public ClientesResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getListaClientes(){
        //@Context final HttpServletResponse response) {
        
//        try {
//            response.setHeader("Access-Control-Allow-Origin","*");
//            response.flushBuffer();
//        } catch (IOException ex) {
//            Logger.getLogger(UsuariosResource.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        return clienteService.listar();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarCliente(Cliente c,
            @Context final HttpServletResponse response) throws IOException{
        try {
            clienteService.inserir(c);
          //  response.setHeader("Access-Control-Allow-Origin","*");
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.flushBuffer();
        } catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
//    @OPTIONS
//    public void autorizarCORS(@Context final HttpServletResponse response){
//        try {
//            response.addHeader("Access-Control-Allow-Origin","*");
//            response.addHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS, PUT, DELETE");
//            response.addHeader("Access-Control-Allow-Headers","Content-Type");
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.flushBuffer();
//        } catch (IOException ex) {
//            Logger.getLogger(UsuariosResource.class.getName()).log(Level.SEVERE, null, ex);
//        }        
//    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getClientesPorId(@PathParam("id") int id){
        return (clienteService.buscarPorId(id));
    }
}

    

