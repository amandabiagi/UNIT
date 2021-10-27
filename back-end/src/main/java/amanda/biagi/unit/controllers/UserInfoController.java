package amanda.biagi.unit.controllers;


import amanda.biagi.unit.models.Documento;
import amanda.biagi.unit.models.InfoUser;
import amanda.biagi.unit.models.UserDoc;
import amanda.biagi.unit.repositories.DocumentoRepository;
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


import javax.websocket.server.PathParam;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/info")
public class UserInfoController {

    private Integer idDoc = 0;

    List<InfoUser> listaHomologacao = new ArrayList<>();
    List<UserDoc> listaUserDoc = new ArrayList<>();
   // List<Documento> listaDocumentos = new ArrayList<>();

    @Autowired
    public InfoUserRepository infoUserRepository;

    @Autowired
    public DocumentoRepository documentoRepository;

    @Autowired
    public InfoUserService infoUserService;


    @PostMapping("/enviar")
    public ResponseEntity<List<UserDoc>> enviar( @RequestParam String infoUserStr, MultipartFile pdf){

        //Recebendo uma string por parametro e transformando em um objeto json
        JSONObject jsonObject = new JSONObject(infoUserStr);

        //Convertendo o objeto json em um objeto.
        UserDoc userDoc = new UserDoc(jsonObject.getLong("ra"),
                jsonObject.getString("nomeAluno"),
                jsonObject.getString("quantidadeHoras"),
                jsonObject.getString("atividade"));

        userDoc.setNomeDocumento(pdf.getOriginalFilename());

        idDoc ++;
        userDoc.setId(idDoc);

        listaUserDoc.add(userDoc);

        return ResponseEntity.created(null).body(listaUserDoc);
    }

    @GetMapping("/exibir")
    public ResponseEntity exibir(){

        if (listaUserDoc.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaUserDoc);
    }

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody List<UserDoc> userDocs){

        InfoUser infoUser = new InfoUser();
        Documento documento = new Documento();

        if (userDocs.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        for (UserDoc userDoc: userDocs) {
            //Criando um documento
            documento.setId(userDoc.getId());
            documento.setNomeDocumento(userDoc.getNomeDocumento());
            documento.setAtividade(userDoc.getAtividade());
            documento.setQuantidadeHoras(userDoc.getQuantidadeHoras());
            documento.setHomologado(userDoc.getHomologado());

            //Criando um usuário
            infoUser.setNomeAluno(userDoc.getNomeAluno());
            infoUser.setRa(userDoc.getRa());

            //Criando o relacionamento
            documento.setFkAluno(infoUser);
            infoUserRepository.save(infoUser);
            documentoRepository.save(documento);

            listaUserDoc.removeIf(u -> Objects.equals(u.getId(), userDoc.getId()));

        }


        return ResponseEntity.accepted().body(listaUserDoc);
    }

    @GetMapping(value = "/download/{nomeDoc}", produces = "application/pdf")
    public ResponseEntity baixar(@PathVariable String nomeDoc) throws FileNotFoundException {
        String caminho = ("src/main/resources/static/imagem/") + nomeDoc;
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity( new FileSystemResource(caminho), headers, HttpStatus.OK);
    }

    @GetMapping("buscar-ra/")
    public ResponseEntity buscarRa(@RequestParam String ra){
        InfoUser infoUser = infoUserRepository.findByRa(Long.parseLong(ra));
        if (infoUser == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(infoUser);

    }


}
