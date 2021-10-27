import api from "../services/api"
import React,{ useState} from "react";


function Botoes(props) {

    const usersHDto = props.usersHDto;
    const setUsersHDto = props.setUsersHDto;

    const objetosChecados=props.objetosChecados
    const setObjetosChecados= props.setObjetosChecados
    

    function salvar() {                

        api.post('/info/salvar',objetosChecados).then((resposta) => {
            if (resposta.status === 201) {
                console.log(resposta)
                window.location.reload();               
                
                setUsersHDto(resposta);
                console.log(usersHDto)
        }
        }).catch((error) => {
            console.log(error);
    })
}

function homologar(){
    objetosChecados.map((infoUserHDto) => {
            infoUserHDto.homologado = true
        setObjetosChecados(objetosChecados => [...objetosChecados])
        console.log(objetosChecados);
        })  
    }

    function naoHomologar(){
        objetosChecados.map((infoUserHDto) => {
            infoUserHDto.homologado = false
            setObjetosChecados(objetosChecados => [...objetosChecados])
        })  
    }

    
    return (    

        <>
            <footer style={{position:" fixed",  left: "0",  height:"10%",  bottom:" 0",  width: "100%", background:"white" }}>
                <div className="btn-group" role="group" >
                    <button   type="button" className="btn btn-primary" style={{ marginLeft: "100px" }} onClick={homologar}>Homologar</button>
                    <button type="button" className="btn"  style={{ marginLeft: "10px" }} onClick={naoHomologar}  >Não homologar</button>
                </div>
                    
             <div className="btn-group" role="group" style={{ float: "right", marginRight: "100px" }} onClick={salvar}>
                    <button type="button" className="btn btn-success">Enviar</button>
                    </div>                    
            </footer>         

        </>
    );
}

export default Botoes;