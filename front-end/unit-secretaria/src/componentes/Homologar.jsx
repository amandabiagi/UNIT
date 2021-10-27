import React, { useEffect, useState } from "react";
import Botoes from "./Botoes";
import Grid from "./Grid";
import Nav from "./Nav"
import api from "../services/api"


function Homologar() {

    const [raAluno, setRaAluno] = useState();
    const [checked, setChecked] = useState();
    const [usersHDto, setUsersHDto] = useState([]);
    const [objetosChecados, setObjetosChecados] = useState([]);

    useEffect(() => {
        try {
            api.get("/info/exibir").then((resposta) => {
                setUsersHDto(resposta.data);
                
            });
            
        } catch (error) {
            console.log(error);
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [])

    return (
        <>
            <Nav
                raAluno={raAluno}
                setRaAluno={setRaAluno}
            />

            <h4>DOCUMENTO PENDENTE PARA HOMOLOGAÇÃO </h4>

            <div className="container " style={{ marginTop: "50px", overflowY: "auto", overflowX: "hidden", height: "60vh" }} >
                <Grid                    
                    usersHDto={usersHDto}
                    setUsersHDto={ setUsersHDto}
                    check={checked}
                    setCheck={setChecked}
                    objetosChecados={objetosChecados}
                    setObjetosChecados={setObjetosChecados}
                />
            </div>
            <footer>
                <Botoes                    
                    usersHDto={usersHDto}
                    setUsersHDto={setUsersHDto}
                    objetosChecados={objetosChecados}
                    setObjetosChecados={setObjetosChecados}
                />
            </footer>
        </>

    );
}

export default Homologar;