package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Cliente;

public class ClienteDaoBd implements ClienteDao {
    private Connection conexao;
    private PreparedStatement comando;

    public Connection conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }

    public void conectarObtendoId(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Erro ao encerrar a conexao");
            throw new BDException(ex);

        }

    }  

    //Metodo salvar:recebe o id auto-increment e já relaciona 
    //no objeto paciente (recebido por parâmetro)
    //Caso queira retornar, só retornar id.
    @Override
    public void salvar(Cliente cliente) {
        int id = 0;
        try {
            String sql = "INSERT INTO cliente (nome, email, empresa, rua, numero,complemento, telefone) "
                    + "VALUES (?,?,?,?,?,?,?)";

            //Foi criado um novo método conectar para obter o id
            conectarObtendoId(sql);
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getEmail());
            comando.setString(3, cliente.getEmpresa());
            comando.setString(4, cliente.getRua());
            comando.setString(5, cliente.getNumero());
            comando.setString(6, cliente.getComplemento());
            comando.setString(7, cliente.getTelefone());
           
            comando.executeUpdate();
            //Obtém o resultSet para pegar o id
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                id = resultado.getInt(1);
                cliente.setId(id);
            }
            else{
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(Cliente cliente) {
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";

            conectar(sql);
            comando.setInt(1, cliente.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(int id) {
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";

            conectar(sql);
            comando.setInt(1, id);
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }
    
    @Override
    public void atualizar(Cliente cliente) {
        try {
            String sql = "UPDATE cliente SET nome=?, email=?, empresa=? , rua=?, numero=? , complemento=? , telefone=?  "
                    + "WHERE id=?";

            conectar(sql);
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getEmail());
            comando.setString(4, cliente.getEmpresa());
            comando.setString(5, cliente.getRua());
            comando.setString(6, cliente.getNumero());
            comando.setString(7, cliente.getComplemento());
            comando.setString(8, cliente.getTelefone());
            comando.setInt(9, cliente.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar usuario no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT id, nome, email, empresa,rua, numero, complemento, telefone FROM cliente";

        try {
            conectar(sql);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");
                String empresa = resultado.getString("empresa");
                String rua = resultado.getString("rua");
                String numero = resultado.getString("numero");
                String complemento = resultado.getString("complemento");
                String telefone = resultado.getString("telefone");

                Cliente clie = new Cliente(id, nome, email, empresa, rua, numero, complemento, telefone);

                listaClientes.add(clie);

            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os usuarios do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (listaClientes);
    }

    
    @Override
    public Cliente procurarPorId(int id) {
        String sql = "SELECT id, nome, email, empresa, rua, numero, complemento, telefone FROM cliente WHERE id = ?";

        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String email = resultado.getString("email");
                String empresa = resultado.getString("empresa");
                String rua = resultado.getString("rua");
                String numero = resultado.getString("numero");
                String complemento = resultado.getString("complemento");
                String telefone = resultado.getString("telefone");

                
                Cliente clie = new Cliente(id, nome, email, empresa, rua, numero, complemento, telefone);

                return clie;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o usuario pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    
}
