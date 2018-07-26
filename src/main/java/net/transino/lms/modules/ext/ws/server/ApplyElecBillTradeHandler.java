package net.transino.lms.modules.ext.ws.server;

import lombok.extern.slf4j.Slf4j;
import net.transino.lms.Config;
import net.transino.lms.modules.ext.ws.DefaultWsRequest;
import net.transino.lms.modules.ext.ws.DefaultWsResponse;
import net.transino.lms.modules.ext.ws.client.XerpWebService;
import net.transino.lms.modules.ext.ws.client.XerpWebServiceHandler;
import net.transino.lms.modules.ext.ws.server.apply.ApplyElecBillTradeRequest;
import net.transino.lms.modules.ext.ws.server.apply.ApplyElecBillTradeResponse;
import net.transino.lms.modules.ext.ws.util.JAXBTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author lee
 * @since 5.0
 */
@Component
@Slf4j
public class ApplyElecBillTradeHandler {
    @Value("#{config.namespaceURI}")
    private String namespaceURI;
    @Value("#{config.localPart}")
    private String localPart;
    @Value("#{config.wsdlLocation}")
    private String wsdlLocation;
    @Autowired
    Config config;
    private IApplyElecBillTrade clientService;

//    @PostConstruct
    private void createTxService() throws MalformedURLException {
        this.wsdlLocation = config.getWsdlLocation();
        log.info("连接服务器：WSDL-" + wsdlLocation);
        URL url = new URL(wsdlLocation);
        QName qname = new QName(namespaceURI, localPart);
        Service service = Service.create(url, qname);
        this.clientService = service.getPort(IApplyElecBillTrade.class);
    }

    public <T extends DefaultWsResponse> Object send(ApplyElecBillTradeRequest request, Class<T> tClass) throws JAXBException {
        try {
            this.createTxService();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String sendXML = JAXBTools.java2Xml(request.getClass(), request);
        log.info("发送报文：XML-" + sendXML);
        ApplyElecBillTradeResponse response = clientService.applyElecBillTrade(request);
        String retXML = JAXBTools.java2Xml(tClass, response);
        log.info("接收报文：XML-" + retXML);
        return response;
    }
}