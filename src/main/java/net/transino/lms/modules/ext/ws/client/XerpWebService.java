package net.transino.lms.modules.ext.ws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author lee
 * @since 5.0
 */
@WebService(name = "TxService", targetNamespace = "http://server.xfire.xerp.nstc.com")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface XerpWebService {
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://server.xfire.xerp.nstc.com")
    public String send(
        @WebParam(name = "in0", targetNamespace = "http://server.xfire.xerp.nstc.com")
            String in0);

}