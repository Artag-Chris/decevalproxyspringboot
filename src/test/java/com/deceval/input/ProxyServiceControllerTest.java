package com.deceval.input;

import com.deceval.DecevalApplicationTest;
import com.deceval.config.BaseIntegration;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DecevalApplicationTest.class, WebTestClient.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProxyServiceControllerTest extends BaseIntegration {

    static {
        System.setProperty("PATH_CLIENT_PROPERTIES", "/home/inerjanuer/inerjanuer/bvc-test/deceval_11/src/main/resources/application-dev.properties");
        System.setProperty("PATH_LOGS", "/home/inerjanuer/inerjanuer/bvc-test/deceval_11/logs/Server.log");
    }

    @Test
    public void testReloadProperties(){
        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.proxy.deceval.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ser:reloadProperties>\n" +
                "      </ser:reloadProperties>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        String response = statusAssertionsWebClient("/services/ProxyServicesImplPort", request, HttpMethod.POST)
                .isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:reloadPropertiesResponse xmlns:ns2=\"http://services.proxy.deceval.com/\" xmlns:ns3=\"http://deceval.com/mrc/solicitud/model/\" xmlns:ns4=\"http://deceval.com/sdl/services/\"><return>Successful</return></ns2:reloadPropertiesResponse></soap:Body></soap:Envelope>", response);
    }
}
