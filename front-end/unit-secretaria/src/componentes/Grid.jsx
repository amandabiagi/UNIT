
import api from "../services/api"


function Grid(props) {

    const setCheck = props.setCheck;
    const usersHDto = props.usersHDto;

     const objetosChecados = props.objetosChecados;
     const setObjetosChecados = props.setObjetosChecados;
    
     {
        if (usersHDto.length > 0) {
            return usersHDto.map((infoUserHDto, index) => {

                function download() {
                    //Montando a chamada da requisição com o backend
                    api.get(`info/download/${infoUserHDto.nomeDocumento}`, { responseType: 'arraybuffer' })
                        .then(function (response) { //Trazendo a resposta da requisição                            

                            const blob = new Blob([response.data], { type: 'application/pdf' });
                            const url = URL.createObjectURL(blob)
                            window.open(url);
                        })
                        .catch(function (error) {
                            console.log(error)
                        });


                }            
        
                return (

                    <div key={index} className="row" style={{ textAlign: "center", width: "80vw", marginLeft: "1vw", marginBottom: "1px" , backgroundColor:"#E8F0FE"}}>                    
                        <div className="col-sm-1">
                            <input className="form-check-input " type="checkbox"
                                onChange={(e) => setCheck(e.target.checked ? setObjetosChecados(objetosChecados => [...objetosChecados, infoUserHDto]) :
                                    setObjetosChecados(objetosChecados.filter(objetosChecados => objetosChecados !== infoUserHDto)))} id="flexCheckDefault" />
                        </div>
                        <div className="col-sm-4"  style={{cursor: "pointer"}}><a onClick={download} style={{color:"#0D6EFD"}}> {infoUserHDto.nomeDocumento}</a></div>
                        <div className="col-sm-2" >{infoUserHDto.atividade}</div>
                        <div className="col-sm-1" >{infoUserHDto.quantidadeHoras}</div>
                        {(infoUserHDto.homologado ? <div className="col-sm-4">Homologado</div> : <div className="col-sm-4">Não homologado</div>)}                  
                    </div>
                )
            })
         } else {
             return(
             <div>

               </div>
             )
        }

   }

}

export default Grid;