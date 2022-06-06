package com.OPT.OPEasy.Util;
// org.apache.poi

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OPT.OPEasy.DTO.ViagemRelatorioDTO;
import com.OPT.OPEasy.Service.TransporteService;
import com.OPT.OPEasy.model.Mercado;
import com.OPT.OPEasy.model.Transporte;
import com.OPT.OPEasy.model.Viagem;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ViagemWritter {
    public int count = 0;
    private boolean id, motoristaNome, nick, valor, 
    avaria, data, transporte, mercado ;

    @Autowired
    TransporteService transporteService;

    public void SetValues(ViagemRelatorioDTO rel){
        this.id = rel.id;
        this.motoristaNome = rel.motoristaNome;
        this.nick = rel.nick;
        this.valor = rel.valor;
        this.avaria = rel.avaria;
        this.transporte = rel.transporte;
        this.mercado = rel.mercado;
        this.data = rel.data;
    }

    public void createReport(List<Viagem> viagemList) throws FileNotFoundException, IOException{
        String path = "/home/link/Área de Trabalho/JavaExcel.xlsx";
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        writteHeader(sheet);
        int rowCount = 1;
        for(Viagem viagem: viagemList){
            Row row = sheet.createRow(rowCount++);
            writteBook(viagem, row);
        }

        try(FileOutputStream outputStream = new FileOutputStream(path)){
            workbook.write(outputStream);
            workbook.close();
        }
        
    }
    public void writteBook(Viagem viagem, Row row){
        count = 0;
        if(id)
            createAndWriteCell(viagem.getId().toString(), row);
        if(motoristaNome)
            createAndWriteCell(viagem.getMotorista().getNome(), row);
        if(nick)
            createAndWriteCell(viagem.getMotorista().getNick(), row);

        getTransporteMercado(viagem, row);

        if(valor)
            createAndWriteCell(String.valueOf(viagem.getValor()), row);
        if(avaria)
            createAndWriteCell(String.valueOf(viagem.getAvaria()), row);
        if(data){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = viagem.getData().format(formatter);
            createAndWriteCell(date, row);
        }
    }

    public int createAndWriteCell(String value, Row row){
        Cell cell = row.createCell(count);
        cell.setCellValue(value);
        return count++;
    }

    public void writteHeader(HSSFSheet sheet){
        count = 0;
        Row row = sheet.createRow(0);
        if(id)
            createAndWriteCell("Ids", row);
        if(motoristaNome)
            createAndWriteCell("Motorista(Nome)", row);
        if(nick)
            createAndWriteCell("Motorista(Nick)", row);
        if(mercado)
            createAndWriteCell("Mercado", row);
        if(transporte)
            createAndWriteCell("Transporte", row);
        if(valor)
            createAndWriteCell("Valor", row);
        if(avaria)
            createAndWriteCell("Avaria", row);
        if(data)
            createAndWriteCell("data", row);

    }       

    private void getTransporteMercado(Viagem viagem,Row row){
        if(!transporte && !mercado)
            return;

        ArrayList<Transporte> transporteList = (ArrayList<Transporte>) transporteService.findByViagem(viagem);
        if(mercado){
            String txt = "";
            for(Transporte trans: transporteList){
                txt += trans.getMercado().getNome();
                txt += "\n";
            }
            createAndWriteCell(txt, row);
        }
        if(transporte){
            String txt = "";
            for(Transporte trans: transporteList){
                txt += trans.getTransporte();
                txt += "\n";
            }
            createAndWriteCell(txt, row);
        }

        return;
    }
    
}
