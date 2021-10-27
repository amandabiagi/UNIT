import React from "react";



function GridConsulta(props) {

    const documentos = props.documentos;
    console.log(documentos);

    return documentos.map((documentos) => {
        return (


            <div className="row" style={{ textAlign: "center", width: "80vw", marginLeft: "10vw", marginBottom: "1px" , backgroundColor:"#E8F0FE"}}>
                <div className="col-sm-3"> {documentos.nomeDocumento} </div>
                <div className="col-sm-2"  >{documentos.atividade} </div>
                <div className="col-sm-2" >{documentos.quantidadeHoras} </div>
                {(documentos.homologado ? <div className="col-sm-4">Homologado</div> : <div className="col-sm-4">Não homologado</div>)}

            </div>

        )
    })
}
export default GridConsulta;

