package amanda.biagi.unit.models;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class InfoUser {

    @Id
    private Long ra;
    private String nomeAluno;
    private String quantidadeHoras;
    private String atividade;
    private Boolean homologado = false;


    public InfoUser(Long ra, String nomeAluno, String quantidadeHoras, String atividade) {
        this.ra = ra;
        this.nomeAluno = nomeAluno;
        this.quantidadeHoras = quantidadeHoras;
        this.atividade = atividade;
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


    public void setHomologado(Boolean status) {
        this.homologado = status;
    }

    public Boolean getHomologado() {
        return homologado;
    }
}
