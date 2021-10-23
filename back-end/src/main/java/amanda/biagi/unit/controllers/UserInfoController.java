package amanda.biagi.unit.controllers;


import amanda.biagi.unit.models.InfoUser;
import amanda.biagi.unit.repositories.InfoUserRepository;
import amanda.biagi.unit.service.InfoUserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/info")
public class UserInfoController {

    List<InfoUser> listHomologacao = new ArrayList<>();

    @Autowired
    public InfoUserRepository infoUserRepository;

    @Autowired
    public InfoUserService infoUserService;


    @PostMapping("/enviar")
    public ResponseEntity<InfoUser> enviar( @RequestParam String infoUserStr, MultipartFile pdf){

        //Recebendo uma string por parametro e transformando em um objeto json
        JSONObject jsonObject = new JSONObject(infoUserStr);


        //Convertendo o objeto json em um objeto desejado, no caso em um infoUser
        InfoUser infoUser = new InfoUser(
                jsonObject.getLong("ra"),
                jsonObject.getString("nomeAluno"),
                jsonObject.getString("quantidadeHoras"),
                jsonObject.getString("atividade")
        );

        infoUser.setNomeDocumento(pdf.getOriginalFilename());
        //Montando o infoUser em um infoUserHDto colocando o nome do arquivo que foi recebido no parametro.


        infoUserService.salvarPDF(pdf);
        listHomologacao.add(infoUser);
        return ResponseEntity.created(null).body(infoUser);
    }

    @GetMapping("/exibir")
    public ResponseEntity exibir(){
        return ResponseEntity.ok(listHomologacao);
    }

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody List<InfoUser> lista) throws Exception{

        if (lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        for (InfoUser infoUser: lista) {
            infoUserRepository.save(infoUser);
        }
        return ResponseEntity.accepted().body(lista);
    }

    @GetMapping(value = "/download/{nomeDoc}", produces = "application/pdf")
    public ResponseEntity baixar(@PathVariable String nomeDoc) throws FileNotFoundException {
        String caminho = ("src/main/resources/static/imagem/") + nomeDoc;

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity( new FileSystemResource(caminho), headers, HttpStatus.OK);
    }

    @GetMapping("buscar-ra/{ra}")
    public ResponseEntity buscarNome(@RequestParam String ra){
        List<InfoUser> infoUser = infoUserRepository.findByRa(Long.parseLong(ra));
        if (infoUser == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(infoUser);
    }








}
