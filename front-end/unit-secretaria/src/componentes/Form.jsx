
import React, { useState, useRef } from "react";
import Api from "../services/api"

function Form() {

    var formData = new FormData();
    const [infoUser, setInfoUser] = useState({});
    const fileElement = useRef(null);


    //Const que mapeia o input e transforma em atributo do obj;
    const handleChangeInfoUser = (event) => {
        const value = { ...infoUser }
        value[event.target.name] = event.target.value;
        setInfoUser(value);
    }

    const handleSubmit = event => {
        enviar();
        event.preventDefault();
    }

    function arquivar() {
        for (const file of fileElement.current.files) {
            formData.append("pdf", file);
        }
    }



    function enviar() {
        formData.append("infoUserStr", JSON.stringify(infoUser));
        Api.post('/info/enviar', formData).then((resposta) => {
            if (resposta.status === 201) {
                window.location.reload();
                
            }
        }).catch((error) => {
            console.log(error);
        })
    }


    return (
        <>
            
            {/* <div class="alert alert-success" role="alert" id="alert"  style={{width: "30%", marginLeft:"35%",  textAlign:"center", marginTop: "2%"}}>
                Formulário enviado com sucesso !
            </div> */}

            <form style={{ width: "50vw", marginLeft: "25vw" }} onSubmit={handleSubmit}>

                <div className="form-group" style={{ marginBottom: "1vw", marginTop: "1vw" }}>
                    <label htmlFor="formGroupExampleInput">RA</label>
                    <input type="text" className="form-control" id="formGroupExampleInput" placeholder="RA" name="ra" onChange={handleChangeInfoUser} />
                </div>

                <div className="form-group" style={{ marginBottom: "1vw", marginTop: "1vw" }}>
                    <label htmlFor="formGroupExampleInput2">Nome</label>
                    <input type="text" className="form-control" id="formGroupExampleInput2" placeholder="Nome" name="nomeAluno" onChange={handleChangeInfoUser} />
                </div>

                <div className="form-group" style={{ marginBottom: "1vw", marginTop: "1vw" }}>
                    <label htmlFor="formGroupExampleInput2">Quantidade de horas</label>
                    <input type="text" className="form-control" id="formGroupExampleInput3" placeholder="Quantidade de horas" name="quantidadeHoras" onChange={handleChangeInfoUser} />
                </div>

                <div className="form-group" style={{ marginBottom: "1vw", marginTop: "1vw" }}>
                    <label htmlFor="formGroupExampleInput2">Tipo de atividade do certificado</label>
                    <input type="text" className="form-control" id="formGroupExampleInput4" placeholder="Tipo de atividade do certificado" name="atividade" onChange={handleChangeInfoUser} />
                </div>

                <div className="form-group" style={{ marginBottom: "1vw", marginTop: "1vw" }}>
                    <input type="file" className="form-control-file" id="exampleFormControlFile1" onChange={arquivar} multiple ref={fileElement} />
                </div>

                <div className="btn-group" role="group" style={{ float: "right" }}>
                    <button type="submit" className="btn btn-success" >Enviar</button>
                </div>

            </form>




        </>

    );
}

export default Form;