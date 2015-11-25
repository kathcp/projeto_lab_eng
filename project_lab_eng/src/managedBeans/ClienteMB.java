package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.dao.DAOException;
import edu.dao.DaoCliente;
import edu.modelo.Cliente;

@ManagedBean (name = "clienteMB")
@SessionScoped
public class ClienteMB implements Serializable{

	private Cliente cliente;
	private DaoCliente daoUsuario;
	private List<Cliente> lista = new ArrayList();
	
	public ClienteMB() { 
		cliente = new Cliente();
		daoUsuario = new DaoCliente();
		 
	}

	public Cliente getUsuario() {
		return cliente;
	}

	public void setCliente(Cliente cli) {
		this.cliente = cli;
	}
	public String adicionar() {
		String msg = "Erro ao adicionar o cliente no banco de dados";
			daoUsuario.adicionar( cliente );
			msg = "Cliente foi adicionado com sucesso no banco de dados";
			cliente = new Cliente();
			
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,  msg, ""));
		return "animais";
	}
	
	public String pesquisar() {
		String msg = "Erro ao pesquisar o animal no banco de dados";
		lista = daoUsuario.listar();
		msg = "Foram localizados " + lista.size() + " Clientes no banco de dados";		
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,  msg, ""));
		return "";
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setClienteAtual(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Cliente> getLista() {
		return lista;
	}
	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}
	
}