package rodarte.testern.viewmodel;

import java.util.List;

import org.zkoss.zul.Messagebox;

import modelcliente.Cliente;
import rodarte.testern.banco.DAO;

public class VmRelatorio {

	private List<Cliente> listaClientes;

	public List<Cliente> getListaClientes() throws Exception {

		DAO objDao = new DAO();

		listaClientes = objDao.listarNome();
		
		//System.out.println(listaClientes);

		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public void verificaImport() throws Exception {

		if (listaClientes.size() == 0) {
			Messagebox.show("Aviso", "Não há dados armazenados na base de dados!", Messagebox.OK, Messagebox.ERROR);
		}
	}
}
