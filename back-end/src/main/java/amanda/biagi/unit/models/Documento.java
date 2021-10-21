package amanda.biagi.unit.models;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Documento {

    @Id
    private Long id;
    private String nomeDocumento;
    private Boolean homologado;

    public Documento(Long id, String nomeDocumento, Boolean homologado) {
        this.id = id;
        this.nomeDocumento = nomeDocumento;
        this.homologado = homologado;
    }

    public Documento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDocumento() {
        return nomeDocumento;
    }

    public void setNomeDocumento(String nomeDocumento) {
        this.nomeDocumento = nomeDocumento;
    }

    public Boolean getStatus() {
        return homologado;
    }

    public void setStatus(Boolean homologado) {
        this.homologado = homologado;
    }
}
