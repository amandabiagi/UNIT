package amanda.biagi.unit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Documento {

    @Id
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkAluno")
    @JsonIgnore
    private InfoUser fkAluno;

    private String nomeDocumento;
    private String quantidadeHoras;
    private String atividade;
    private Boolean homologado = false;

    public Documento(String nomeDocumento, String quantidadeHoras, String atividade) {
        this.nomeDocumento = nomeDocumento;
        this.quantidadeHoras = quantidadeHoras;
        this.atividade = atividade;
        this.homologado = false;
    }

    public Documento() {

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

    public InfoUser getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(InfoUser fkAluno) {
        this.fkAluno = fkAluno;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "id=" + id +
                ", fkAluno=" + fkAluno +
                ", nomeDocumento='" + nomeDocumento + '\'' +
                ", quantidadeHoras='" + quantidadeHoras + '\'' +
                ", atividade='" + atividade + '\'' +
                ", homologado=" + homologado +
                '}';
    }
}
