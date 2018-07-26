package net.transino.lms.modules.ext.ws.server;

import lombok.extern.slf4j.Slf4j;
import net.transino.lms.Config;
import net.transino.lms.modules.ext.ws.DefaultWsResponse;
import net.transino.lms.modules.ext.ws.server.notify.NotifyElecBillStatusRequest;
import net.transino.lms.modules.ext.ws.server.notify.NotifyElecBillStatusResponse;
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
public class NotifyElecBillTradeHandler {
    @Value("#{config.namespaceURI2}")
    private String namespaceURI;
    @Value("#{config.localPart2}")
    private String localPart;
    @Value("#{config.wsdlLocation2}")
    private String wsdlLocation;
    @Autowired
    Config config;
    private INotifyElecBillStatus clientService;

    //    @PostConstruct
    private void createTxService() throws MalformedURLException {
        this.wsdlLocation = config.getWsdlLocation2();
        log.info("连接服务器：WSDL-" + wsdlLocation);
        URL url = new URL(wsdlLocation);
        QName qname = new QName(namespaceURI, localPart);
        Service service = Service.create(url, qname);
        this.clientService = service.getPort(INotifyElecBillStatus.class);
    }

    public <T extends DefaultWsResponse> Object send(NotifyElecBillStatusRequest request, Class<T> tClass) throws JAXBException {
        try {
            this.createTxService();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String sendXML = JAXBTools.java2Xml(request.getClass(), request);
        log.info("发送报文：XML-" + sendXML);
        NotifyElecBillStatusResponse response = clientService.notifyElecBillStatus(request);
        String retXML = JAXBTools.java2Xml(tClass, response);
        log.info("接收报文：XML-" + retXML);
        return response;
    }
}