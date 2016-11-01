package com.zeftech.sfapi.config;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.sforce.ws.SessionRenewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.namespace.QName;

/**
 * Created by Cody_OPI on 10/31/16.
 */

@Configuration
public class SoapConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SoapConfig.class);

    @Value("${sfSoap.login.loginUri}")
    private String loginUri;

    @Value("${sfSoap.login.username}")
    private String username;

    @Value("${sfSoap.login.password}")
    private String password;

    @Value("${sfSoap.login.securityToken}")
    private String securityToken;

    @Value("${sfSoap.config.prettyPrintXml}")
    private Boolean prettyPrintXml;


    @Bean
    public PartnerConnection partnerConnection() {
        PartnerConnection pc = null;
        try {
            ConnectorConfig cc = buildConnectorConfig();
            cc.setSessionRenewer(new SFSessionRenewer());
            pc = Connector.newConnection(cc);
        } catch (ConnectionException e) {
            LOG.info("{}", e);
        }
        return pc;
    }

    public ConnectorConfig buildConnectorConfig() {
        ConnectorConfig cc = new ConnectorConfig();
        cc.setUsername(this.username);
        cc.setPassword(this.password + this.securityToken);
        cc.setAuthEndpoint(this.loginUri);
        cc.setPrettyPrintXml(this.prettyPrintXml);
        return cc;
    }

    public class SFSessionRenewer implements SessionRenewer {
        @Override
        public SessionRenewalHeader renewSession(ConnectorConfig config) throws ConnectionException {
            ConnectorConfig cc = buildConnectorConfig();
            PartnerConnection pc = Connector.newConnection(cc);
            SessionRenewalHeader header = new SessionRenewalHeader();
            header.name = new QName("urn:partner.soap.sforce.com", "SessionHeader");
            header.headerElement = pc.getSessionHeader();
            return header;
        }
    }


}