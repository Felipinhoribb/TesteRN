package rodarte.testern.viewmodel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

import Sheet.Export;
import Sheet.Import;
import modelcliente.Cliente;


public class VmPageInicio {

	private Media arquivoTemporario;
	
	private boolean visibilidadeBotaoLerEProcessar = true;
	
	@Command
	public void uploadArquivo(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws Exception {
		
		UploadEvent event = (UploadEvent) ctx.getTriggerEvent();

		arquivoTemporario = event.getMedia();
		
		BindUtils.postNotifyChange(null, null, VmPageInicio.this, "nomeArquivo");
		
	}
	
	@Command
	public void lerArquivoEProcessar() {
		
		try {
			
			InputStream arquivo = arquivoTemporario.getStreamData();
			
			Import imp = new Import();
			
			//Método resposável por fazer a importação da planilha
			imp.importarPlanilha(arquivo);
			
			visibilidadeBotaoLerEProcessar = false;
			
			BindUtils.postNotifyChange(null, null, VmPageInicio.this, "visibilidadeBotaoLerEProcessar");
			
			Clients.showNotification("Processamento realizado com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null, 3500, true);
			
		} catch (Exception ex) {
			
			Messagebox.show(ex.getMessage(), "Erro ao Ler e Processar Arquivo", Messagebox.OK, Messagebox.ERROR);
			
		} 
		
	}
	
	@Command
	public void exportarResultados() {
		
		/**
		 * Exportação dos arquivos do banco de dados
		 */
		
		try {
			
			File arquivo = new File("Resultados.xlsx").getCanonicalFile();
			OutputStream out = new FileOutputStream(arquivo.getAbsolutePath());
			
			//Implementação da exportação do arquivo
			Export exp = new Export();
			//exp.exportarClientesNome(out);
			
			exp.exportarClientesNotas(out);
			
			//exp.exportarClientesEstatisticas(out);
			
			Filedownload.save(arquivo, ".xlsx");
			
			out.close();
			
			Clients.showNotification("Arquivo exportado com sucesso!", Clients.NOTIFICATION_TYPE_INFO, null, null, 3500, true);
			
		} catch (Exception ex) {

			Messagebox.show(ex.getMessage(), "Erro ao Exportar Resultados", Messagebox.OK, Messagebox.ERROR);
			
		}
		
	}
	
	@Command
	public void resetarImportacao() {
		
		arquivoTemporario = null;
		
		visibilidadeBotaoLerEProcessar = true;
		
		BindUtils.postNotifyChange(null, null, VmPageInicio.this, "visibilidadeBotaoLerEProcessar");
		BindUtils.postNotifyChange(null, null, VmPageInicio.this, "nomeArquivo");
		
		Clients.showNotification("Atenção quanto aos dados que foram persistidos no banco!", Clients.NOTIFICATION_TYPE_WARNING, null, null, 3500, true);
		
	}
	
	public String getNomeArquivo() {
		
		return arquivoTemporario == null ? "" : arquivoTemporario.getName();
		
	}
	
	public boolean getVisibilidadeBotaoLerEProcessar() {
		
		return this.visibilidadeBotaoLerEProcessar;
		
	}
	
	
}
