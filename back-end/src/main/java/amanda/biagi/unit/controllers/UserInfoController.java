package amanda.biagi.unit.controllers;


import amanda.biagi.unit.models.InfoUser;
import amanda.biagi.unit.models.dto.InfoUserHDto;
import amanda.biagi.unit.repositories.InfoUserRepository;
import amanda.biagi.unit.service.InfoUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/info")
public class UserInfoController {

    List<InfoUserHDto> listHomologacao = new ArrayList<>();

    @Autowired
    public InfoUserRepository infoUserRepository;

    @Autowired
    public InfoUserService infoUserService;


    @PostMapping("/enviar")
    public ResponseEntity<InfoUserHDto> enviar( @RequestParam String infoUserStr, MultipartFile pdf){

        //Recebendo uma string por parametro e transformando em um objeto json
        JSONObject jsonObject = new JSONObject(infoUserStr);

        //Convertendo o objeto json em um objeto desejado, no caso em um infoUser
        InfoUser infoUser = new InfoUser(
                jsonObject.getLong("ra"),
                jsonObject.getString("nomeAluno"),
                jsonObject.getString("quantidadeHoras"),
                jsonObject.getString("atividade")
        );

        //Montando o infoUser em um infoUserHDto colocando o nome do arquivo que foi recebido no parametro.
        InfoUserHDto infoUserHDto = new InfoUserHDto(infoUser, pdf.getOriginalFilename());

        infoUserService.salvarPDF(pdf);
        listHomologacao.add(infoUserHDto);
        return ResponseEntity.created(null).body(infoUserHDto);
    }

    @GetMapping("/exibir")
    public ResponseEntity exibir(){
        return ResponseEntity.ok(listHomologacao);
    }

    @PostMapping("/salvar")
    public ResponseEntity salvar(){
        return ResponseEntity.created(null).build();
    }

    @GetMapping(value = "/download/{nomeDoc}", produces = "application/pdf")
    public ResponseEntity baixar(@PathVariable String nomeDoc) throws FileNotFoundException {
        String caminho = ("src/main/resources/static/imagem/") + nomeDoc;

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity( new FileSystemResource(caminho), headers, HttpStatus.OK);
    }

    @PostMapping("/homologar/{nomeDoc}")
    public void homologar(@PathVariable String nomeDoc){

        for (InfoUserHDto infoUserHDto: listHomologacao) {
            if(infoUserHDto.getNomeDocumento().equals(nomeDoc)){
                infoUserHDto.setStatus(true);
            }
        }

    }

    @PostMapping("/nao-homologar/{nomeDoc}")
    public void naoHomologar(@PathVariable String nomeDoc){

        for (InfoUserHDto infoUserHDto: listHomologacao) {
            if(infoUserHDto.getNomeDocumento().equals(nomeDoc)){
                infoUserHDto.setStatus(false);
            }
        }

    }



}
