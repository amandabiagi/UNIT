package amanda.biagi.unit.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class InfoUser {

    @Id
    private Long ra;
    private String nomeAluno;
    @OneToMany(mappedBy = "fkAluno",targetEntity = Documento.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Documento> documentos;


    public InfoUser(Long ra, String nomeAluno) {
        this.ra = ra;
        this.nomeAluno = nomeAluno;
        this.documentos = new ArrayList<>();
    }

    public InfoUser() {
    }

    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

//    public void setDocumentos(List<Documento> documentos) {
//        this.documentos = documentos;
//    }

    public void setDocumentos(Documento documentos) {
        this.documentos.add(documentos);
    }



    @Override
    public String toString() {
        return "InfoUser{" +
                "ra=" + ra +
                ", nomeAluno='" + nomeAluno + '\'' +
                ", documentos=" + documentos +
                '}';
    }
}
