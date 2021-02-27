package Sheet;

import java.io.InputStream;
import java.sql.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import modelcliente.Cliente;
import rodarte.testern.banco.DAO;

public class Import {

	public Import() {
		// TODO Auto-generated constructor stub
	}

	// Realiza a leitura dos dados da planilha e Salva no Banco de dados
	public void importarPlanilha(InputStream arquivo) throws Exception {

		/*
		 * Implementação da leitura do arquivo Criação de workbook para manipular
		 * XSSF usado para arquivo xlsx (planilha) e recuperação da primeira aba da planilha
		 */

		XSSFWorkbook workbook = new XSSFWorkbook(arquivo);
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Instância do objeto DAO (relacionado a manipulação de dados no db)
		DAO objDao = new DAO();

		Iterator<Row> rowIterator = sheet.iterator();

		// Percorre as linhas da planilha
		while (rowIterator.hasNext()) {

			// recebe cada linha da planilha
			Row row = rowIterator.next();

			// descarta o cabeçalho da planilha
			if (row.getRowNum() == 0) {
				continue;
			}

			// recebe todas as celulas da respectiva linha e instancia o model de pessoas
			Iterator<Cell> cellIterator = row.iterator();
			Cliente objCli = new Cliente();

			// Percorre todas as células da linha atual
			while (cellIterator.hasNext()) {

				// Criação de uma célula
				Cell cell = cellIterator.next();

				// Recupera os valores das células e atribui aos seus respectivos atributos do objCli
				switch (cell.getColumnIndex()) {
				case 0:
					objCli.setIdentificacao(((Double) cell.getNumericCellValue()).longValue());

					break;
				case 1:
					objCli.setNome(cell.getStringCellValue());

					break;
				case 2:
					objCli.setSexo(cell.getStringCellValue());

					break;
				case 3:
					objCli.setDataNascimento(cell.getDateCellValue());

					break;
				case 4:
					objCli.setNota1(((Double) cell.getNumericCellValue()).intValue());

					break;
				case 5:
					objCli.setNota2(((Double) cell.getNumericCellValue()).intValue());

					break;
				case 6:
					objCli.setNota3(((Double) cell.getNumericCellValue()).intValue());

					break;

				default:
					break;
				}
			}
			// Salva o cliente da respectiva linha no db
			objDao.salvarCliente(objCli);
		}

		// Encerra a abertura da planilha
		workbook.close();
	}

}
