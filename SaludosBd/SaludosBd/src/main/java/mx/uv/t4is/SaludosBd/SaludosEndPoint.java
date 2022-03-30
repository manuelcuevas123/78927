package mx.uv.t4is.SaludosBd;

import java.util.ArrayList;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import https.t4is_uv_mx.saludos.*;
import https.t4is_uv_mx.saludos.BuscarSaludosResponse.Saludos;

@Endpoint
public class SaludosEndPoint {

    //ArrayList<Saludos> lista = new ArrayList<>();
    //private int i = 1;

    @Autowired
    private Isaludadores isaludadores;


    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "SaludarRequest")
    @ResponsePayload
    public SaludarResponse saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Hola "+ peticion.getNombre());
        //lista.add(nombre.getNombre());
        Saludadores e = new Saludadores();//Saludos e = new Saludos();
        //e.setId(i++);
        e.setNombre(peticion.getNombre());
        isaludadores.save(e); //lista.add(e);
        return respuesta;
    }   
    
    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "BuscarSaludosRequest")
    @ResponsePayload
    public BuscarSaludosResponse buscar(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        //Implementar la devolución de la lista
        //for(Saludos s : lista){
            //System.out.println(s);
            //Saludos e = new Saludos();
            //e.setNombre(s);
            //e.setId(1);
            //respuesta.getSaludos().add(s);
        //}
        for(Saludadores s:isaludadores){
            System.out.print(s);
            Saludadores e = new Saludadores();
            e.setId(1);
            isaludadores.findAll(s);
        }
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "BorrarSaludosRequest")
    @ResponsePayload
    public BorrarSaludoResponse modificar(@RequestPayload BorrarSaludoRequest peticion) {
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();
        Saludadores e = new Saludadores();
        e.setId(peticion.getId());
        for(Saludadores s: isaludadores){
            if(peticion.getId()==e.getId()){
                isaludadores.deleteById(peticion.getId());
                break;
            }
        }
        //for(Saludos s : lista){
        //    if(peticion.getId()==s.getId()){
        //       lista.remove(lista.indexOf(s));
        //        break;
           // }
       // }
        respuesta.setRespuesta(true);
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "ModificarSaludosRequest")
    @ResponsePayload
    public ModificarSaludoResponse modificar(@RequestPayload ModificarSaludoRequest peticion) {
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        Saludos e = new Saludos();
        e.setNombre(peticion.getNombre());
        e.setId(peticion.getId());
        //lista.set(peticion.getId() -1, e);

        respuesta.setRespuesta(true);
        return respuesta;
    }
    
}