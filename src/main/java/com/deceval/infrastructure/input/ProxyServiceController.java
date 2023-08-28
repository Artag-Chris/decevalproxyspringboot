package com.deceval.infrastructure.input;

import com.deceval.infrastructure.client.ProxyServiceClient;
import com.deceval.mrc.solicitud.WsGenerarTokenPortType;
import com.deceval.sdl.services.CambioEstadoPagareDTO;
import com.deceval.sdl.services.RadicacionSolicitudRequestNatural;
import com.deceval.sdl.services.RegistroFirmarPagaresXmlDTO;
import com.deceval.sdl.services.SDLService;
import com.deceval.sdl.services.SolicitudAmortizacionSaldosServiceDTO;
import com.deceval.sdl.services.SolicitudAnulacionPagaresDTO;
import com.deceval.sdl.services.SolicitudCancelacionPagaresServiceDTO;
import com.deceval.sdl.services.SolicitudCertificadoFirmaPDTO;
import com.deceval.sdl.services.SolicitudConsultaPagareServiceXMLDTO;
import com.deceval.sdl.services.SolicitudConsultarPagareDTO;
import com.deceval.sdl.services.SolicitudCrearGiradorDaneServiceDTO;
import com.deceval.sdl.services.SolicitudCrearGiradorServiceDTO;
import com.deceval.sdl.services.SolicitudCrearPagareDaneServiceDTO;
import com.deceval.sdl.services.SolicitudCrearPagareServiceDTO;
import com.deceval.sdl.services.SolicitudDesembolsoAnotarCuentaDTO;
import com.deceval.sdl.services.SolicitudFirmarPagaresCaracteresDTO;
import com.deceval.sdl.services.SolicitudFirmarPagaresConCertificadoDTO;
import com.deceval.sdl.services.SolicitudFirmarPagaresDTO;
import com.deceval.sdl.services.SolicitudGeneracionMasivaCertificadosDTO;
import com.deceval.sdl.services.SolicitudPagaresFirmadosDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.Serializable;

/**
 * Esta clase se encarga de exponer los servicios a los cuales el cliente tendra acceso
 * aca podra habilitar o inhabilitar los servicios que necesite
 * @author inerjanuerBernate
 */
@WebService(targetNamespace = "http://services.proxy.deceval.com/", portName = "ProxyServicesImplPort", serviceName = "ProxyServicesImplService")
public class ProxyServiceController implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProxyServiceClient sdlClient;

    public ProxyServiceController() {
        sdlClient = new ProxyServiceClient();
    }

    @WebMethod(operationName = "notificacionPagaresFirmados", action = "urn:NotificacionPagaresFirmados")
    public com.deceval.sdl.services.RespuestaPagaresFirmadosDTO notificacionPagaresFirmados(@WebParam(name = "arg0") SolicitudPagaresFirmadosDTO notificacionPagaresFirmados) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.notificacionPagaresFirmados(notificacionPagaresFirmados);
    }

    @WebMethod(operationName = "creacionGiradores", action = "urn:CreacionGiradores")
    public com.deceval.sdl.services.RespuestaCrearGiradorServiceDTO creacionGiradores(@WebParam(name = "arg0") SolicitudCrearGiradorServiceDTO creacionGiradores) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.creacionGiradores(creacionGiradores);
    }

    @WebMethod(operationName = "cancelacionPagares", action = "urn:CancelacionPagares")
    public com.deceval.sdl.services.RespuestaCancelacionPagaresServiceDTO cancelacionPagares(@WebParam(name = "arg0") SolicitudCancelacionPagaresServiceDTO cancelacionPagares) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.cancelacionPagares(cancelacionPagares);
    }

    @WebMethod(operationName = "consultarPagaresNopdf", action = "urn:ConsultarPagaresNopdf")
    public com.deceval.sdl.services.RespuestaConsultarPagaresNopdfDTO consultarPagaresNopdf(@WebParam(name = "arg0") SolicitudConsultarPagareDTO consultarPagaresNopdf) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.consultarPagaresNopdf(consultarPagaresNopdf);
    }

    @WebMethod(operationName = "consultarPagares", action = "urn:ConsultarPagares")
    public com.deceval.sdl.services.RespuestaConsultarPagaresDTO consultarPagares(@WebParam(name = "arg0") SolicitudConsultarPagareDTO consultarPagares) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.consultarPagares(consultarPagares);
    }

    @WebMethod(operationName = "creacionPagaresFisicosCodificado", action = "urn:CreacionPagaresFisicosCodificado")
    public com.deceval.sdl.services.RespuestaDocumentoPagareDaneServiceDTO creacionPagaresFisicosCodificado(@WebParam(name = "arg0") SolicitudCrearPagareDaneServiceDTO creacionPagaresFisicosCodificado) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.creacionPagaresFisicosCodificado(creacionPagaresFisicosCodificado);
    }

    @WebMethod(operationName = "registrarCustodiaPagare", action = "urn:RegistrarCustodiaPagare")
    public com.deceval.sdl.services.RespuestaFirmarPagaresXmlDTO registrarCustodiaPagare(@WebParam(name = "arg0") RegistroFirmarPagaresXmlDTO registrarCustodiaPagare) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.registrarCustodiaPagare(registrarCustodiaPagare);
    }

    @WebMethod(operationName = "firmarPagareConCertificado", action = "urn:FirmarPagareConCertificado")
    public com.deceval.sdl.services.RespuestaFirmarPagaresDTO firmarPagareConCertificado(@WebParam(name = "arg0") SolicitudFirmarPagaresConCertificadoDTO firmarPagareConCertificado) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.firmarPagareConCertificado(firmarPagareConCertificado);
    }

    @WebMethod(operationName = "registrarAmortizacion", action = "urn:RegistrarAmortizacion")
    public com.deceval.sdl.services.RespuestaAmortizacionSaldosServiceDTO registrarAmortizacion(@WebParam(name = "arg0") SolicitudAmortizacionSaldosServiceDTO registrarAmortizacion) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.registrarAmortizacion(registrarAmortizacion);
    }

    @WebMethod(operationName = "creacionGiradoresCodificados", action = "urn:CreacionGiradoresCodificados")
    public com.deceval.sdl.services.RespuestaCrearGiradorDaneServiceDTO creacionGiradoresCodificados(@WebParam(name = "arg0") SolicitudCrearGiradorDaneServiceDTO creacionGiradoresCodificados) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.creacionGiradoresCodificados(creacionGiradoresCodificados);
    }

    @WebMethod(operationName = "notificacionDesembolsoAnotacionEnCuenta", action = "urn:NotificacionDesembolsoAnotacionEnCuenta")
    public com.deceval.sdl.services.RespuestaDesembolsoAnotarCuentaDTO notificacionDesembolsoAnotacionEnCuenta(@WebParam(name = "arg0") SolicitudDesembolsoAnotarCuentaDTO notificacionDesembolsoAnotacionEnCuenta) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.notificacionDesembolsoAnotacionEnCuenta(notificacionDesembolsoAnotacionEnCuenta);
    }

    @WebMethod(operationName = "consultaPagareXML", action = "urn:ConsultaPagareXML")
    public com.deceval.sdl.services.RespuestaConsultaPagareXMLServiceDTO consultaPagareXML(@WebParam(name = "arg0") SolicitudConsultaPagareServiceXMLDTO consultaPagareXML) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.consultaPagareXML(consultaPagareXML);
    }

    @WebMethod(operationName = "firmarPagares", action = "urn:FirmarPagares")
    public com.deceval.sdl.services.RespuestaFirmarPagaresDTO firmarPagares(@WebParam(name = "arg0") SolicitudFirmarPagaresDTO firmarPagares) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.firmarPagares(firmarPagares);
    }

    @WebMethod(operationName = "cambiarEstadoProvisionalADefinitivo", action = "urn:CambiarEstadoProvisionalADefinitivo")
    public com.deceval.sdl.services.RespuestaCambiarEstadoProvisionalADefinitivoDTO cambiarEstadoProvisionalADefinitivo(@WebParam(name = "arg0") CambioEstadoPagareDTO cambiarEstadoProvisionalADefinitivo) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.cambiarEstadoProvisionalADefinitivo(cambiarEstadoProvisionalADefinitivo);
    }

    @WebMethod(operationName = "firmarPagareCaracteres", action = "urn:FirmarPagareCaracteres")
    public com.deceval.sdl.services.RespuestaFirmarPagaresCodigoErrorDTO firmarPagareCaracteres(@WebParam(name = "arg0") SolicitudFirmarPagaresCaracteresDTO firmarPagareCaracteres) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.firmarPagareCaracteres(firmarPagareCaracteres);
    }

    @WebMethod(operationName = "firmarPagaresAsincrono", action = "urn:FirmarPagaresAsincrono")
    public com.deceval.sdl.services.RespuestaFirmarPagaresAsincronoDTO firmarPagaresAsincrono(@WebParam(name = "arg0") SolicitudFirmarPagaresDTO firmarPagaresAsincrono) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.firmarPagaresAsincrono(firmarPagaresAsincrono);
    }

    @WebMethod(operationName = "creacionPagares", action = "urn:CreacionPagares")
    public com.deceval.sdl.services.RespuestaDocumentoPagareServiceDTO creacionPagares(@WebParam(name = "arg0") SolicitudCrearPagareServiceDTO creacionPagares) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.creacionPagares(creacionPagares);
    }

    @WebMethod(operationName = "generacionMasivaCertificados", action = "urn:GeneracionMasivaCertificados")
    public com.deceval.sdl.services.RespuestaGeneracionMasivaCertificadosDTO generacionMasivaCertificados(@WebParam(name = "arg0") SolicitudGeneracionMasivaCertificadosDTO generacionMasivaCertificados) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.generacionMasivaCertificados(generacionMasivaCertificados);
    }

    @WebMethod(operationName = "crearCertificadoFirma", action = "urn:CrearCertificadoFirma")
    public com.deceval.sdl.services.RespuestaCrearCertificadoFirmaDTO crearCertificadoFirma(@WebParam(name = "arg0") SolicitudCertificadoFirmaPDTO crearCertificadoFirma) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.crearCertificadoFirma(crearCertificadoFirma);
    }

    @WebMethod(operationName = "anulacionPagares", action = "urn:AnulacionPagares")
    public com.deceval.sdl.services.RespuestaAnulacionPagaresDTO anulacionPagares(@WebParam(name = "arg0") SolicitudAnulacionPagaresDTO anulacionPagares) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.anulacionPagares(anulacionPagares);
    }

    @WebMethod(operationName = "solicitudRadicacionFichaTecnicaNatural", action = "urn:SolicitudRadicacionFichaTecnicaNatural")
    public com.deceval.sdl.services.RespuestaRadicacionErrorDTO solicitudRadicacionFichaTecnicaNatural(@WebParam(name = "arg0") RadicacionSolicitudRequestNatural solicitudRadicacionFichaTecnicaNatural) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.solicitudRadicacionFichaTecnicaNatural(solicitudRadicacionFichaTecnicaNatural);
    }

    @WebMethod(operationName = "creacionPagaresCodificado", action = "urn:CreacionPagaresCodificado")
    public com.deceval.sdl.services.RespuestaDocumentoPagareDaneServiceDTO creacionPagaresCodificado(@WebParam(name = "arg0") SolicitudCrearPagareDaneServiceDTO creacionPagaresCodificado) {
        SDLService service = sdlClient.getService(SDLService.class);
        return service.creacionPagaresCodificado(creacionPagaresCodificado);
    }


    @WebMethod(operationName = "generarToken", action = "urn:GenerarToken")
    public com.deceval.mrc.solicitud.model.InformacionFirmaPagareTO generarToken(@WebParam(name = "arg0") com.deceval.mrc.solicitud.model.InformacionPagareTO informacionPagareTO) {
        WsGenerarTokenPortType service = sdlClient.getService(WsGenerarTokenPortType.class);
        return service.generarToken(informacionPagareTO);
    }

}
