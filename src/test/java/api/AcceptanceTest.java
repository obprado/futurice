package api;

import com.obprado.futurice.CalculusApp;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;

public abstract class AcceptanceTest {

    public static final int PORT = 80;
    public static final String HOST = "http://localhost:" + PORT;
    protected HttpResponse response;
    protected String responseBody;
    private static CalculusApp calculusApp = new CalculusApp();

    @BeforeClass
    public static void setUp() throws Exception {
        calculusApp.run();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        calculusApp.stop();
    }

    protected void whenWeMakeAGetRequestTo(final String uri) throws IOException {
        HttpUriRequest httpUriRequest = new HttpGet(HOST + uri);
        response = HttpClientBuilder.create().build().execute(httpUriRequest);
        responseBody = EntityUtils.toString(response.getEntity());
    }
}
