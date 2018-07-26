package net.transino.lms.modules.ext.ws.client;

import lombok.extern.slf4j.Slf4j;
import net.transino.lms.modules.ext.ws.DefaultWsRequest;
import net.transino.lms.modules.ext.ws.DefaultWsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import net.transino.lms.modules.ext.ws.util.JAXBTools;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.ws.Service;

/**
 * @author lee
 * @since 5.0
 */
@Slf4j
//@Component
public class XerpWebServiceHandler {
    @Value("#{config.namespaceURI}")
    private String namespaceURI;
    @Value("#{config.localPart}")
    private String localPart;
    @Value("#{config.wsdlLocation}")
    private String wsdlLocation;
    private XerpWebService clientService;

    @PostConstruct
    private void createTxService() throws MalformedURLException {
        log.info("连接服务器：WSDL-" + wsdlLocation);
        URL url = new URL(wsdlLocation);
        QName qname = new QName(namespaceURI, localPart);
        Service service = Service.create(url, qname);
        this.clientService = service.getPort(XerpWebService.class);
    }

    public <T extends DefaultWsResponse> Object send(DefaultWsRequest request, Class<T> tClass) throws JAXBException {
        String sendXML = JAXBTools.java2Xml(request.getClass(), request);
        log.info("发送报文：XML-" + sendXML);
        String retXML = clientService.send(sendXML);
        log.info("接收报文：XML-" + retXML);
        return JAXBTools.xml2Java(tClass, retXML);
    }
}