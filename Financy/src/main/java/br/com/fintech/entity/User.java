package br.com.fintech.entity;

import java.sql.Date;

import br.com.fintech.utils.Criptografia;

public class User {
	private int cdUser;
	private String nome;
	private String sexo;
	private Date dtNascimento;
	private String email;
	private String senha;
	
	public User(){
	}

	public User(String nome,String sexo, Date date,
			String email, String senha){

		this.nome = nome;
		this.sexo = sexo;
		this.dtNascimento = date;
		this.email = email;
		setSenha(senha);
	}
	
	public User(int cdUser, String nome,String sexo, Date date,
			String email, String senha){
		this.cdUser = cdUser;
		this.nome = nome;
		this.sexo = sexo;
		this.dtNascimento = date;
		this.email = email;
		setSenha(senha);
	}
	
	public int getCdUser() {
		return cdUser;
	}

	public String getNome() {
		return nome;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	

	public Date getDtNascimento() {
		return dtNascimento;
	}
	
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		try {
			this.senha = Criptografia.criptografar(senha);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
