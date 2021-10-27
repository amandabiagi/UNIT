package amanda.biagi.unit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserDoc {

    private Long ra;
    private String nomeAluno;
    private Integer id;
    private String nomeDocumento;
    private String quantidadeHoras;
    private String atividade;
    private Boolean homologado = false;

    public UserDoc(Long ra, String nomeAluno, String quantidadeHoras, String atividade) {
        this.ra = ra;
        this.nomeAluno = nomeAluno;
        this.quantidadeHoras = quantidadeHoras;
        this.atividade = atividade;

    }

    public UserDoc() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeDocumento() {
        return nomeDocumento;
    }

    public void setNomeDocumento(String nomeDocumento) {
        this.nomeDocumento = nomeDocumento;
    }

    public String getQuantidadeHoras() {
        return quantidadeHoras;
    }

    public void setQuantidadeHoras(String quantidadeHoras) {
        this.quantidadeHoras = quantidadeHoras;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public Boolean getHomologado() {
        return homologado;
    }

    public void setHomologado(Boolean homologado) {
        this.homologado = homologado;
    }


}
