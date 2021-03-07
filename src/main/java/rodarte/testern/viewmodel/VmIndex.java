package rodarte.testern.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

import rodarte.testern.ModeloInformacoesProjeto;

public class VmIndex {

	private final ModeloInformacoesProjeto informacoesDoProjeto = new ModeloInformacoesProjeto(); 
	
	@Command
	public void abrirTelaArquivos() {
		
		Executions.createComponents("pageArquivos.zul", null, null);
		
	}
	
	@Command
	public void abrirTelaRelatorio() throws Exception {
		
		Executions.createComponents("pageRelatorio.zul", null, null);
		
		VmRelatorio objRel = new VmRelatorio();
		objRel.verificaImport();
		
	}
	
	public ModeloInformacoesProjeto getInformacoesDoProjeto() {
		
		return this.informacoesDoProjeto;
		
	}
	
}
