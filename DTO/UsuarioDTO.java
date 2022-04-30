package DTO;

//Vamos fazer aqui todo o processo de encapsulamento
//classe publica para todo mundo enxergar
//porem com variaveis privadas com os métodos acessores
public class UsuarioDTO {
    
    private int id_usuario;
    private String nome_usuario, senha_usuario;

    
    //A gente utiliza o set para atribuir os valores que o usuario digita para a variável
    //E o get pra pegar esses valores que contem nessa variável
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }
    
    //Vamos receber por parametro o String nome_usuario e atribuir a var this.nome_usuario
    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }
    
    
}
