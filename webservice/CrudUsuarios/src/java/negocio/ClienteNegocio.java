package negocio;

import dao.ClienteDao;
import dao.ClienteDaoBd;
import dao.UsuarioDao;
import dao.UsuarioDaoBd;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import model.Cliente;
import model.Usuario;

@Stateless
public class ClienteNegocio {
    ClienteDao clienteDao;
    
    public ClienteNegocio(){
        clienteDao = new ClienteDaoBd();
    }
    
    public void inserir(Cliente c) throws Exception{
        if(c.getNome() == null || c.getNome().isEmpty())
            throw new Exception("Necessário colocar nome!");        
        clienteDao.salvar(c);
    }
    
    public List<Cliente> listar(){
        return clienteDao.listar();
    }
    
    public Cliente buscarPorId(int id){
        return clienteDao.procurarPorId(id);
    }
     public Cliente deletar(int id) throws Exception{
        Cliente cliente = clienteDao.procurarPorId(id);
        if (cliente==null){
            throw new Exception("Cliente nao encontrado");
        }
        clienteDao.deletar(id);
        return cliente;
    }
    
    public void atualizar(Cliente c, int id) throws Exception{
        Cliente cliente = clienteDao.procurarPorId(id);
        if(cliente == null){
            throw new Exception("Cliente nao encontrado");
        }
        if(c.getNome()!=null && !c.getNome().isEmpty()){
            cliente.setNome(c.getNome());
        }
        if(c.getEmail()!=null && !c.getEmail().isEmpty()){
        cliente.setEmail(c.getEmail());
        }
        if(c.getNome()!=null && !c.getNome().isEmpty()){
            cliente.setNome(c.getNome());
        }
        clienteDao.atualizar(cliente);
    }

}
