package amanda.biagi.unit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class InfoUserService {

    @Value("${caminho}")
    private String caminho;

    @Value("${diretorio}")
    private String diretorio;




public void salvarPDF(MultipartFile pdf){
    this.salvar(this.diretorio, pdf);
}

public void salvar(String diretorio,MultipartFile pdf){


    Path diretorioPath = Paths.get(this.caminho, diretorio);
    Path arquivoPath = diretorioPath.resolve(pdf.getOriginalFilename());

    try{
        Files.createDirectories(diretorioPath).getNameCount();
        pdf.transferTo(arquivoPath);
    }catch (IOException erro){
        throw new RuntimeException("Problema na hora de criar um arquivo");
    }
}



}
