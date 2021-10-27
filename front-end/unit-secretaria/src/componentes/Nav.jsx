import React, { useState} from "react";
import LogoPng from "../imagens/LOGOSPNG.png";
import api from "../services/api"


function Nav(props) {

    const setDocumentos = props.setDocumentos;
    const setInfoUser = props.setInfoUser;
    const [raAluno, setAluno] = useState("");
   

    const handleChangeInfoUser = (event) => {
        const value = { ...raAluno }
        value[event.target.name] = event.target.value; //Pegando o que o usuário esta digitando
        setAluno(value.raAluno);        
    }

    const handleSubmit = event => {
        buscar();
        event.preventDefault();
        
    }
 
    function buscar() {             

        api.get(`/info/buscar-ra/?ra=${raAluno}`).then((resposta) => {
            if (resposta.status === 200) {
                setInfoUser(resposta.data);
                setDocumentos(resposta.data.documentos);                    
        }
        }).catch((error) => {
            console.log(error);
    })
}
 
    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
                        <img src={LogoPng} alt="" style={ {width:"100px"}} />
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <a className="nav-link active" aria-current="page" href="http://localhost:3000">Homologação</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link active" href="http://localhost:3000/consulta">Consulta</a>
                            </li>
                            
                            <li className="nav-item">
                                <a className="nav-link" href="http://localhost:3000/simulacao">Simulação</a>
                            </li>
                            
                        </ul>
                        {props.consulta ?
                         <>
                            <form className="d-flex" onSubmit={handleSubmit}>
                                    <input name="raAluno" className="form-control me-2" type="search" placeholder="Buscar aluno" aria-label="Search"  onChange={ handleChangeInfoUser} />
                                    <button className="btn btn-primary" type="submit" >Buscar</button>
                            </form>
                        </>
                            :
                            ""}                    
                        
                    </div>
                </div>
            </nav>

        </>
    );
}


export default Nav;