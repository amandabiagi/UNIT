import React, { useState } from "react";
import GridConsulta from "./GridConsulta";
import Nav from "./Nav"


function Consulta() {

    const [infoUser, setInfoUser] = useState({});
    const [documentos, setDocumentos] = useState([]);

    return (
        <>
            <Nav
                consulta
                infoUser={infoUser}
                setInfoUser={setInfoUser}
                setDocumentos={setDocumentos}
            />
            <div>
                <p> Ra: {infoUser.ra}</p>
                <p> Nome:{infoUser.nomeAluno}</p>
            </div>

            <h4>DOCUMENTOS </h4>
            <GridConsulta
                documentos={documentos}
            />

        </>

    );
}

export default Consulta;