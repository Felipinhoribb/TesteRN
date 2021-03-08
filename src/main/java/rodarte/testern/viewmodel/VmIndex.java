package rodarte.testern.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

import rodarte.testern.ModeloInformacoesProjeto;

public class VmIndex {

	private final ModeloInformacoesProjeto informacoesDoProjeto = new ModeloInformacoesProjeto();

	@Command
	public void abrirTelaArquivos() {

		Executions.createComponents("pageArquivos.zul", null, null);

	}

	// Método que verifica se a tela deve ser aberta

	@Command
	public void abrirTelaRelatorio() throws Exception {

		VmRelatorio objRel = new VmRelatorio();

		// Verifica se há dados importados na base de dados e exibe os dados
		if (objRel.verificaImport()) {
			Executions.createComponents("pageRelatorio.zul", null, null);
		} else {
			Messagebox.show("Importação",
					"Não ha dados armazenados na base de dados!\n" + "Importe os dados para a exibição dos dados",
					Messagebox.OK, Messagebox.INFORMATION);
		}
	}

	public ModeloInformacoesProjeto getInformacoesDoProjeto() {

		return this.informacoesDoProjeto;

	}

}
