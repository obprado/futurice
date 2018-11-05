package api;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculusTest extends AcceptanceTest {

    @Test
    public void shouldCalculateAValidExpression() throws IOException {
        whenWeMakeAGetRequestTo("/calculus?query=MiAqICgyMy8oMyozKSktIDIzICogKDIqMyk=");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(responseBody).isEqualTo("{ \n" +
                "  \"error\": \"false\",\n" +
                "  \"result\": \"-132.8889\"\n" +
                "}");
    }
}
