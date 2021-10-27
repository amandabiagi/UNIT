import React from "react";
import Homologar from "./componentes/Homologar";
import "../src/estilos/app.css"
import {  BrowserRouter,  Route} from 'react-router-dom'
import Consulta from "./componentes/Consulta";
import Simulado from "./componentes/Simulado";



function App() {

  return (

    <div>
     
      <BrowserRouter>
        <Route path="/" exact component={Homologar}></Route>
        <Route path="/consulta" component={Consulta}></Route>
        <Route path="/simulacao" component={Simulado}></Route>
        

      </BrowserRouter>
      
     </div>
    
  );
}

export default App;
