package amanda.biagi.unit.models.dto;

import amanda.biagi.unit.models.InfoUser;

public class InfoUserHDto {

    private String nomeDocumento;
    private String quantidadeHoras;
    private String atividade;
    private Boolean homologado;

    public InfoUserHDto(InfoUser infoUser, String nomeDocumento) {
        this.homologado = infoUser.getHomologado();
        this.nomeDocumento = nomeDocumento;
        this.quantidadeHoras = infoUser.getQuantidadeHoras();
        this.atividade = infoUser.getAtividade();
    }

    public InfoUserHDto() {
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

    public Boolean getStatus() {
        return homologado;
    }

    public void setStatus(Boolean status) {
        this.homologado = status;
    }


}
