package mx.uv.t4is.saludos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.tis_uv_mx.saludos.BorrarSaludoRequest;
import https.tis_uv_mx.saludos.BorrarSaludoResponse;
import https.tis_uv_mx.saludos.BuscarSaludosResponse;
import https.tis_uv_mx.saludos.ModificarSaludoRequest;
import https.tis_uv_mx.saludos.ModificarSaludoResponse;
import https.tis_uv_mx.saludos.SaludarRequest;
import https.tis_uv_mx.saludos.SaludarResponse;
import https.tis_uv_mx.saludos.BuscarSaludosResponse.Saludos;


@Endpoint
public class SaludosEndPoint {

    ArrayList<Saludos> lista = new ArrayList<>();
    private int i = 1;

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "SaludarRequest")
    
    @ResponsePayload
    public SaludarResponse saludar(@RequestPayload SaludarRequest nombre){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Hola"+ nombre.getNombre());
        //agregar el saludo a una lista
        Saludos e = new Saludos();
        e.setId(i++);
        e.setNombre(nombre.getNombre());
        lista.add(e);
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "BuscarSaludosRequest")
    @ResponsePayload
    public BuscarSaludosResponse buscar(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        //implementar la devolución de la lista
        for(Saludos s : lista){
            //System.out.println(s);
            //Saludos e = new Saludos();
            //e.setNombre(s);
            respuesta.getSaludos().add(s);
        }
        
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "ModificarSaludoRequest")
    @ResponsePayload
    public ModificarSaludoResponse modificar(@RequestPayload ModificarSaludoRequest peticion){
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        Saludos e = new Saludos();
        e.setNombre(peticion.getNombre());
        e.setId(peticion.getId());
        lista.set(peticion.getId(), e);
        respuesta.setRespuesta(true);
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/saludos", localPart = "BorrarSaludoRequest")
    @ResponsePayload
    public BorrarSaludoResponse modificar(@RequestPayload BorrarSaludoRequest peticion){
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();
        Saludos e = new Saludos();
        for(Saludos s: lista){
            if(peticion.getId() == s.getId()){
                lista.remove(s);
            }
        }
        //e.setId(peticion.getId());
        lista.remove(peticion.getId()-1);
        respuesta.setRespuesta(true);
        return respuesta;
    }
}