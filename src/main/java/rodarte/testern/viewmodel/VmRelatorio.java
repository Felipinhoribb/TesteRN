package rodarte.testern.viewmodel;

import java.util.List;

import org.zkoss.zul.Messagebox;

import modelcliente.Cliente;
import rodarte.testern.banco.DAO;

public class VmRelatorio {

	private List<Cliente> listaClientes;
	
	public VmRelatorio() throws Exception {
		DAO objDao = new DAO();
		listaClientes = objDao.listarNome();
	}

	/*
	 * Métodos get e set que recuperam os dados da lista de clientes
	 */
	
	public List<Cliente> getListaClientes() throws Exception {

		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	//Verifica se há dados importados
	public boolean verificaImport() {

		boolean value = false;
		
		if (listaClientes.size() != 0) {
			return true;
		}
		
		return value;
	}
}
