package com.netflix.eureka.resources;

import com.netflix.appinfo.EurekaAccept;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.util.EurekaEntityComparators;
import com.netflix.discovery.converters.wrappers.CodecWrappers;
import com.netflix.discovery.converters.wrappers.DecoderWrapper;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.discovery.util.InstanceInfoGenerator;
import com.netflix.eureka.AbstractTester;
import com.netflix.eureka.Version;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 单元测试抽象类的子类
 *
 * @author David Liu
 */
public class ApplicationsResourceTest extends AbstractTester {
    private ApplicationsResource applicationsResource;
    private Applications testApplications;

    /**
     * 在方法执行前，此方法会执行，初始化Eureka-Server模拟环境
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        InstanceInfoGenerator instanceInfos = InstanceInfoGenerator.newBuilder(20, 6).build();
        testApplications = instanceInfos.toApplications();

        applicationsResource = new ApplicationsResource(serverContext);

        for (Application application : testApplications.getRegisteredApplications()) {
            for (InstanceInfo instanceInfo : application.getInstances()) {
                registry.register(instanceInfo, false);
            }
        }
    }

    /**
     * 因为是模拟环境，对 Eureka-Server 的操作不是 Eureka-Client 请求 Eureka-Server 的方式，而是直接调用单元测试对应的方法
     */
    @Test
    public void testFullAppsGetJson() throws Exception {
        // 直接调用getContainers方法
        Response response = applicationsResource.getContainers(
                Version.V2.name(),
                MediaType.APPLICATION_JSON,
                null, // encoding
                EurekaAccept.full.name(),
                null,  // uriInfo
                null  // remote regions
        );

        String json = String.valueOf(response.getEntity());
        DecoderWrapper decoder = CodecWrappers.getDecoder(CodecWrappers.LegacyJacksonJson.class);

        Applications decoded = decoder.decode(json, Applications.class);
        // test per app as the full apps list include the mock server that is not part of the test apps
        for (Application application : testApplications.getRegisteredApplications()) {
            Application decodedApp = decoded.getRegisteredApplications(application.getName());
            assertThat(EurekaEntityComparators.equal(application, decodedApp), is(true));
        }
    }

    @Test
    public void testFullAppsGetGzipJsonHeaderType() throws Exception {
        Response response = applicationsResource.getContainers(
                Version.V2.name(),
                MediaType.APPLICATION_JSON,
                "gzip", // encoding
                EurekaAccept.full.name(),
                null,  // uriInfo
                null  // remote regions
        );

        assertThat(response.getMetadata().getFirst("Content-Encoding").toString(), is("gzip"));
        assertThat(response.getMetadata().getFirst("Content-Type").toString(), is(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFullAppsGetGzipXmlHeaderType() throws Exception {
        Response response = applicationsResource.getContainers(
                Version.V2.name(),
                MediaType.APPLICATION_XML,
                "gzip", // encoding
                EurekaAccept.full.name(),
                null,  // uriInfo
                null  // remote regions
        );

        assertThat(response.getMetadata().getFirst("Content-Encoding").toString(), is("gzip"));
        assertThat(response.getMetadata().getFirst("Content-Type").toString(), is(MediaType.APPLICATION_XML));
    }

    @Test
    public void testMiniAppsGet() throws Exception {
        Response response = applicationsResource.getContainers(
                Version.V2.name(),
                MediaType.APPLICATION_JSON,
                null, // encoding
                EurekaAccept.compact.name(),
                null,  // uriInfo
                null  // remote regions
        );

        String json = String.valueOf(response.getEntity());
        DecoderWrapper decoder = CodecWrappers.getDecoder(CodecWrappers.LegacyJacksonJson.class);

        Applications decoded = decoder.decode(json, Applications.class);
        // test per app as the full apps list include the mock server that is not part of the test apps
        for (Application application : testApplications.getRegisteredApplications()) {
            Application decodedApp = decoded.getRegisteredApplications(application.getName());
            // assert false as one is mini, so should NOT equal
            assertThat(EurekaEntityComparators.equal(application, decodedApp), is(false));
        }

        for (Application application : testApplications.getRegisteredApplications()) {
            Application decodedApp = decoded.getRegisteredApplications(application.getName());
            assertThat(application.getName(), is(decodedApp.getName()));
            // now do mini equals
            for (InstanceInfo instanceInfo : application.getInstances()) {
                InstanceInfo decodedInfo = decodedApp.getByInstanceId(instanceInfo.getId());
                assertThat(EurekaEntityComparators.equalMini(instanceInfo, decodedInfo), is(true));
            }
        }
    }
}
