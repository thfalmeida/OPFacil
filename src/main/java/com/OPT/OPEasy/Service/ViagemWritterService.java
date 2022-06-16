// package com.OPT.OPEasy.Service;
// // org.apache.poi

// import java.io.FileNotFoundException;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.time.format.DateTimeFormatter;
// import java.util.List;

// import org.apache.poi.hssf.usermodel.HSSFCellStyle;
// import org.apache.poi.hssf.usermodel.HSSFSheet;
// import org.apache.poi.hssf.usermodel.HSSFWorkbook;
// import org.apache.poi.ss.usermodel.Cell;
// import org.apache.poi.ss.usermodel.CellStyle;
// import org.apache.poi.ss.usermodel.FillPatternType;
// import org.apache.poi.ss.usermodel.IndexedColors;
// import org.apache.poi.ss.usermodel.Row;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.OPT.OPEasy.DTO.ViagemRelatorioDTO;

// import com.OPT.OPEasy.model.Transporte;


// @Service
// public class ViagemWritterService {

//     short bodyForegroundColor = IndexedColors.GREY_25_PERCENT.getIndex();
//     short headerForegroundColor = IndexedColors.GREY_40_PERCENT.getIndex();

//     ViagemRelatorioDTO rel;
//     @Autowired
//     TransporteService transporteService;

//     public List<Transporte> generateReport(ViagemRelatorioDTO rel) throws FileNotFoundException, IOException{
//         this.rel = rel;
//         List<Transporte> transps = transporteService.listAll();
//         createReport(transps);
//         return transps;
//     }

//     private void createReport(List<Transporte> transporteList) throws FileNotFoundException, IOException{
//         String path = "/home/link/Área de Trabalho/JavaExcel.xlsx";
//         HSSFWorkbook workbook = new HSSFWorkbook();
//         HSSFCellStyle style = createCellStyle(workbook, bodyForegroundColor);
//         HSSFSheet sheet = workbook.createSheet();
        
//         writteHeader(sheet);

//         int rowCount = 1;
//         Long viagemId = (long) -1;
//         boolean styleCell = false;
//         for(Transporte transp: transporteList){
//             Row row = sheet.createRow(rowCount++);
//             writteBook(transp, row);

//             //Intercala as cores do background das celulas a partir 
//             //do id de viagem
//             Long actualViagemId = transp.getViagem().getId();
//             if(actualViagemId != viagemId){
//                 styleCell = !styleCell;
//                 viagemId = actualViagemId;
//             }
                
            
//             if(styleCell)
//                 stylizeRow(row, style);
//         }

//         try(FileOutputStream outputStream = new FileOutputStream(path)){
//             workbook.write(outputStream);
//             workbook.close();
//         }
        
//     }
//     public void writteBook(Transporte transp, Row row){
//         rel.setCount(0);
//         if(rel.id)

//             createAndWriteCell(transp.getViagem().getId().toString(), row);
//         if(rel.motoristaNome)
//             createAndWriteCell(transp.getViagem().getMotorista().getNome(), row);
//         if(rel.nick)
//             createAndWriteCell(transp.getViagem().getMotorista().getNick(), row);

//         getTransporteMercado(transp, row);

//         if(rel.valor)
//             createAndWriteCell(String.valueOf(transp.getViagem().getValor()), row);
//         if(rel.avaria)
//             createAndWriteCell(String.valueOf(transp.getViagem().getAvaria()), row);
//         if(rel.data){
//             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//             String date = transp.getViagem().getData().format(formatter);
//             createAndWriteCell(date, row);
//         }
//     }

//     public int createAndWriteCell(String value, Row row){
//         Cell cell = row.createCell(rel.count);
//         cell.setCellValue(value);
//         return rel.count++;
//     }

//     public void writteHeader(HSSFSheet sheet){
//         rel.count = 0;
//         Row row = sheet.createRow(0);
//         if(rel.id)
//             createAndWriteCell("Ids", row);
//         if(rel.motoristaNome)
//             createAndWriteCell("Motorista(Nome)", row);
//         if(rel.nick)
//             createAndWriteCell("Motorista(Nick)", row);
//         if(rel.mercado)
//             createAndWriteCell("Mercado", row);
//         if(rel.transporte)
//             createAndWriteCell("Transporte", row);
//         if(rel.valor)
//             createAndWriteCell("Valor", row);
//         if(rel.avaria)
//             createAndWriteCell("Avaria", row);
//         if(rel.data)
//             createAndWriteCell("data", row);

//         HSSFCellStyle style = createCellStyle(sheet.getWorkbook(), headerForegroundColor);
//         stylizeRow(row, style);
//     }       

//     private void getTransporteMercado(Transporte transp,Row row){
//         if(!rel.transporte && !rel.mercado)
//             return;

//         if(rel.mercado){
//             String txt = transp.getMercado().getNome();
            
//             createAndWriteCell(txt, row);
//         }
//         if(rel.transporte){
//             String txt = transp.getTransporte().toString();
//             createAndWriteCell(txt, row);
//         }

//         return;
//     }

//     private HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short color){
//         HSSFCellStyle style = workbook.createCellStyle();
//         style.setFillForegroundColor(color);
//         style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//         return style;
//     }


//     private void stylizeRow(Row row, CellStyle style){
//         for (Cell cell : row) {
//             cell.setCellStyle(style);
//         }
//     }
    
// }
