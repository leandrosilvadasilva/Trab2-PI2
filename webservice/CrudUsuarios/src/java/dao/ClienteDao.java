package dao;

import model.Usuario;
import java.util.List;
import model.Cliente;

public interface ClienteDao {
    public void salvar(Cliente cliente);
    public void deletar(Cliente cliente);
    public void deletar(int id);
    public void atualizar(Cliente cliente);
    public List<Cliente> listar();
    public Cliente procurarPorId(int id);
    
}
