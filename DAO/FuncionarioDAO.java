package DAO;

import DTO.FuncionarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAO {

    //Essas var estão fora do método para poder acessá-las diretamente da classe
    //Assim, não preciso usar sempre o método.
    Connection conn;            //para entrar com método de conexao
    PreparedStatement pstm;     //para preparar a declaração do comando sql
    ResultSet rs;               //para retornar a pesquisa direto do banco
    ArrayList<FuncionarioDTO> lista = new ArrayList<>();//classe para formar uma lista dos dados pesquisados no banco

    //Crio o método público que não precisa retornar nada
    public void cadastrarFuncionario(FuncionarioDTO objfuncionariodto) {
        //Crio var String que vai conter o comando sql de inserção de dados
        String sql = "insert into funcionario (nome_funcionario,endereco_funcionario, cod_cargo) values (?,?,?)";

        //Toda operação que a gente fazer no banco, precisamos abrir a classe ConexãoDAO.
        //Com a var conn do tipo Connection eu vou acessar a classe ConexaoDAO e então o método conectaBD.
        conn = new ConexaoDAO().conectaBD();

        try {
            //(pstm)estou preparando minha conexao.(conn)abrindo minha classe de conexao.
            //(.prepareStatement)preparando e passando meu comando sql.
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getNome_funcionario());
            pstm.setString(2, objfuncionariodto.getEndereco_funcionario());
            pstm.setInt(3, objfuncionariodto.getCod_cargo());

            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Cadastrar: " + erro);
        }
    }

    //Criamos um novo método dentro da classe FuncionarioDAO que será responsável
    //por fazer uma pesquisa dentro do banco de dados.
    //Método público do tipo ResultSet que vai usar objeto da classe FuncionarioDTO
    public ArrayList<FuncionarioDTO> PesquisarFuncionario() {
        //Cria var para comando sql relativo a tabela de funcionario da base de dados
        String sql = "select * from funcionario";

        //Toda operação que a gente fazer no banco, precisamos abrir a classe ConexãoDAO.
        //Com a var conn do tipo Connection eu vou acessar a classe ConexaoDAO e então o método conectaBD.
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            //chamar var ResultSet para executar a pesquisa do bd com base no comando sql
            rs = pstm.executeQuery();
            //Criar estrutura de repetição para enquanto estiver linha (next)
            //ele vai executar a Query.
            while (rs.next()) {
                //Estou instanciando objeto da classe FuncionarioDTO e em seguida
                //aplicando método para setar o id_funcionario tendo origem o 
                //id_funcionario do banco de dados por meio do método da var ResultSet rs.
                //No cadastro, a gente seta o funcionariodto e manda pro sql. Na consulta
                //a gente faz o caminho inverso. Pega do banco e seta o objfuncionariodto.
                FuncionarioDTO objfuncionariodto = new FuncionarioDTO();
                objfuncionariodto.setId_funcionario(rs.getInt("id_funcionario"));
                objfuncionariodto.setNome_funcionario(rs.getString("nome_funcionario"));
                objfuncionariodto.setEndereco_funcionario(rs.getString("endereco_funcionario"));

                //Lista que o while vai preenchendo a cada repetição
                lista.add(objfuncionariodto);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Pesquisar: " + erro);
        }
        return lista;
    }

    //Vamos criar o método de conexão referente a funcionalidad de alteração de funcionario
    //Este método vai receber como parâmetro os objetos da classe FunionarioDTO
    public void alterarFuncionario(FuncionarioDTO objfuncionariodto){
        String sql = "update funcionario set nome_funcionario = ?, endereco_funcionario = ? where id_funcionario = ?";
        
        conn = new ConexaoDAO().conectaBD();
        
         try {
            //(pstm)estou preparando minha conexao.(conn)abrindo minha classe de conexao.
            //(.prepareStatement)preparando e passando meu comando sql.
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getNome_funcionario());
            pstm.setString(2, objfuncionariodto.getEndereco_funcionario());
            pstm.setInt(3, objfuncionariodto.getId_funcionario());

            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Alterar: " + erro);
        }        
    }
    
    //Este é o método criado para funcionalidade de exclusão do bd do funcionario
    //Vamos entrar com comando sql para deletar o registro do funcionario com base no id
    public void excluirFuncionario(FuncionarioDTO objfuncionariodto){
        String sql = "delete from funcionario where id_funcionario = ?";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {
            //(pstm)estou preparando minha conexao.(conn)abrindo minha classe de conexao.
            //(.prepareStatement)preparando e passando meu comando sql.
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objfuncionariodto.getId_funcionario());
            
            pstm.execute();
            pstm.close();            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Funcionario Excluir" + erro);
        }
    }
    
    //Vamos criar um método para fazer um select na tabela cargo
    public ResultSet listarCargo() {
      conn = new ConexaoDAO().conectaBD();
      String sql = "select * from cargo order by descricao_cargo";
      
        try {
            
            pstm = conn.prepareStatement(sql);
            return pstm.executeQuery();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ListarCargo FuncionarioDAO" + erro.getMessage());
            return null;
        }        
    }
}   
