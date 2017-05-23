package com.idle.game.server;

import com.idle.game.core.constant.IdleConstants;
import java.io.File;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.config.logging.Level;
import org.wildfly.swarm.config.logging.Logger;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jpa.JPAFraction;
import org.wildfly.swarm.logging.LoggingFraction;
import org.wildfly.swarm.logging.LoggingProperties;
import org.wildfly.swarm.undertow.WARArchive;
import org.wildfly.swarm.undertow.descriptors.WebXmlAsset;

/**
 *
 * @author rafael
 */
public class Main {

    public static void main(String args[]) throws Exception {

        Swarm container = new Swarm(args);

        container.fraction(new DatasourcesFraction()
                .jdbcDriver("h2", (d) -> {
                    d.driverClassName("org.h2.Driver");
                    d.xaDatasourceClass("org.h2.jdbcx.JdbcDataSource");
                    d.driverModuleName("com.h2database.h2");
                })
                .dataSource("idleDS", (ds) -> {
                    ds.driverName("h2");
                    ds.connectionUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
                    ds.userName("sa");
                    ds.password("sa");
                })
        );

        container.fraction(new JPAFraction()
                .defaultDatasource("jboss/datasources/idleDS")
        );

        Level leveLog;

        if (System.getProperty(LoggingProperties.LOGGING) != null) {
            leveLog = Level.valueOf(System.getProperty(LoggingProperties.LOGGING));
        } else {
            leveLog = Level.ALL;
        }

        container.fraction(new LoggingFraction()
                .logger(new Logger(IdleConstants.LOG_NAME).level(leveLog))
                .logger(new Logger("org.hibernate.SQL").level(leveLog))
                .logger(new Logger("org.hibernate.type.descriptor.sql.BasicBinder").level(leveLog))
        );

//        container.fraction(SecurityFraction.defaultSecurityFraction()
//                .securityDomain(new SecurityDomain("idleDomain")
//                        .cacheType(SecurityDomain.CacheType.DEFAULT)
//                        .classicAuthentication(new ClassicAuthentication()
//                                .loginModule(new LoginModule("idleLoginModule")
//                                        .code("com.idle.game.security.IdleLoginModule")
//                                        .flag(Flag.REQUIRED)
//                                )))
//        );
        container.start();

        WARArchive deployment = ShrinkWrap.createFromZipFile(WARArchive.class, new File("target/idleserver.war"));

        WebXmlAsset webXmlAsset = deployment.findWebXmlAsset();
//        webXmlAsset.setLoginConfig("BASIC", "QGSdomain");
//        webXmlAsset.protect("/api/secure/*").withRole("*");

//        webXmlAsset.setContextParam("productionMode", "false");
//        deployment.setSecurityDomain("QGSdomain");
        //Or, you can add web.xml and jboss-web.xml from classpath or somewhere
        //deployment.addAsWebInfResource(new ClassLoaderAsset("WEB-INF/web.xml", Main.class.getClassLoader()), "web.xml");
        //deployment.addAsWebInfResource(new ClassLoaderAsset("WEB-INF/jboss-web.xml", Main.class.getClassLoader()), "jboss-web.xml");
        container.deploy(deployment);

    }

}
