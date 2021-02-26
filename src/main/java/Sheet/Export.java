package Sheet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import modelcliente.Cliente;
import rodarte.testern.banco.DAO;

public class Export {

	public Export() {
		// TODO Auto-generated constructor stub
	}

	public void exportarClientesNome(OutputStream arquivoSaida) throws Exception {

		/*
		 * Implementação da exportação dos dados do db para a planilha Criação da aba de
		 * Clientes por ordem de nomes
		 */

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Clientes");

		// Definindo o Tamanho da linha e coluna
		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeight((short) 400);

		// Instância da classe DAO
		// Recuperação dos dados do db
		DAO objDao = new DAO();
		ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) objDao.listarNome();

		// Variáveis auxiliares para controlar o número de linhas e colunas
		int rownum = 0;
		int cellnum = 0;

		Row rowHeader;
		Cell cellHeader;

		// Criação dos cabeçalhos
		rowHeader = sheet.createRow(rownum++);
		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Identificação");

		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Nome");

		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Sexo");

		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Data Nascimento");

		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Nota 1º Trimestre");

		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Nota 2º Trimestre");

		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Nota 3º Trimestre");

		/*
		 * Criação de linhas e colunas da planilha Inserçao de dados nas células das
		 * linhas
		 */

		for (Cliente cli : listaClientes) {

			// Cria as linhas da tabela
			Row row = sheet.createRow(rownum++);

			// Inicia a criação das células e insere os atributos de cada cliente
			cellnum = 0;

			Cell cellIdentificacao = row.createCell(cellnum++);
			cellIdentificacao.setCellValue(cli.getIdentificacao());

			Cell cellNome = row.createCell(cellnum++);
			cellNome.setCellValue(cli.getNome());

			Cell cellSexo = row.createCell(cellnum++);
			cellSexo.setCellValue(cli.getSexo());

			Cell cellDataNascimento = row.createCell(cellnum++);
			cellDataNascimento.setCellValue(cli.getDataNascimento());

			Cell cellNota1 = row.createCell(cellnum++);
			cellNota1.setCellValue(cli.getNota1());

			Cell cellNota2 = row.createCell(cellnum++);
			cellNota2.setCellValue(cli.getNota2());

			Cell cellNota3 = row.createCell(cellnum++);
			cellNota3.setCellValue(cli.getNota3());
		}

		// Executa a escrita dos dados no arquivo
		workbook.write(arquivoSaida);
	}

	public void exportarClientesNotas(OutputStream arquivoSaida) throws Exception {

		/*
		 * Criação da aba de notas por idade
		 */

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Notas");

		// Definindo o Tamanho das linhas e colunas
		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeight((short) 400);

		// Instância da classe DAO
		// Recuperação dos dados do db
		DAO objDao = new DAO();
		ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) objDao.listarIdade();

		// Variáveis auxiliares para controlar o número de linhas e colunas
		int rownum = 0;
		int cellnum = 0;

		Row rowHeader;
		Cell cellHeader;

		// Criação dos cabeçalhos
		rowHeader = sheet.createRow(rownum++);
		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Identificação");

		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Nome");

		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Idade");

		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Média de Notas");

		/*
		 * Criação de linhas e colunas da planilha Inserçao de dados nas células das
		 * linhas
		 */

		for (Cliente cli : listaClientes) {

			// Cria as linhas da tabela
			Row row = sheet.createRow(rownum++);

			// Cria as células e insere os atributos de cada cliente
			cellnum = 0;

			Cell cellIdentificacao = row.createCell(cellnum++);
			cellIdentificacao.setCellValue(cli.getIdentificacao());

			Cell cellNome = row.createCell(cellnum++);
			cellNome.setCellValue(cli.getNome());

			// Recuperando a idade do Cliente
			Cell cellIdade = row.createCell(cellnum++);
			cellIdade.setCellValue(cli.getDataNascimento().toLocalDate().getYear() - 2021);

			Cell cellMediaNotas = row.createCell(cellnum++);
			cellMediaNotas.setCellValue(cli.getNota1() + cli.getNota2() + cli.getNota3() / 3);
		}

		// Executa a escrita dos dados no arquivo
		workbook.write(arquivoSaida);
	}

	public void exportarClientesEstatisticas(OutputStream arquivoSaida) throws Exception {

		/*
		 * Criação da aba de estatísticas por sexo
		 */

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Estatísticas");

		// Definindo o Tamanho das linhas e colunas
		sheet.setDefaultColumnWidth(15);
		sheet.setDefaultRowHeight((short) 400);

		// Instância da classe DAO
		// Recuperação dos dados separados pelas listas de clientes masculinos e
		// femininos
		DAO objDao = new DAO();
		ArrayList<Cliente> listaClientesMasculino = (ArrayList<Cliente>) objDao.listarMasculino();
		ArrayList<Cliente> listaClientesFeminino = (ArrayList<Cliente>) objDao.listarFeminino();

		// Variáveis auxiliares para controlar o número de linhas e colunas
		int rownum = 0;
		int cellnum = 0;

		Row rowHeader;
		Cell cellHeader;

		// Criação do cabeçalho
		rowHeader = sheet.createRow(rownum++);
		cellHeader = rowHeader.createCell(cellnum++);
		cellHeader.setCellValue("Estatísticas");

		/*
		 * Percorre as listas dos dois sexos recuperado pelo banco de dados e recebe os
		 * valores totais para realizar o cálculo da média
		 */

		int totalClientes = listaClientesFeminino.size() + listaClientesMasculino.size();
		int totalAprovadosMasculino = 0;
		int totalAprovadosFeminino = 0;

		for (Cliente cli : listaClientesMasculino) {

			int total;

			// Soma as notas dos clientes masculinos
			total = cli.getNota1() + cli.getNota2() + cli.getNota3();

			// Verifica se ele foi aprovado
			if (total >= 70) {
				totalAprovadosMasculino++;
				System.out.println("Aprovado");
			}

		}

		for (Cliente cli : listaClientesFeminino) {
			int total;

			// Soma as notas dos clientes femininos
			total = cli.getNota1() + cli.getNota2() + cli.getNota3();

			// Verifica se ele foi aprovado
			if (total >= 70) {
				totalAprovadosFeminino++;
				System.out.println("Aprovado");
			}
		}

		// Cria as linhas da tabela
		Row row = sheet.createRow(rownum++);

		// Cria as células e insere os atributos de cada cliente
		cellnum = 0;

		// Relatório de clientes do sexo masculino
		Cell cellTitleMasculino = row.createCell(cellnum++);
		cellTitleMasculino.setCellValue("Percentual de Clientes Aprovados do Sexo Masculino");

		Cell cellPercMasculino = row.createCell(cellnum++);
		cellPercMasculino.setCellValue((totalAprovadosMasculino * 100) / totalClientes);

		// Relatório de clientes do sexo feminino
		Cell cellTitleFeminino = row.createCell(cellnum++);
		cellTitleFeminino.setCellValue("Percentual de Clientes do Sexo Feminino");

		Cell cellPercFeminino = row.createCell(cellnum++);
		cellPercFeminino.setCellValue((totalAprovadosFeminino * 100) / totalClientes);

		// Executa a escrita dos dados no arquivo
		workbook.write(arquivoSaida);
	}

}
