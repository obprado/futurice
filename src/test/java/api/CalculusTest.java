package api;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculusTest extends AcceptanceTest {

    @Test
    public void shouldCalculateAValidExpression() throws IOException {
        // 'MiAqICgyMy8oMyozKSktIDIzICogKDIqMyk=' decodes to '2 * (23/(3*3))- 23 * (2*3)'
        whenWeMakeAGetRequestTo("/calculus?query=MiAqICgyMy8oMyozKSktIDIzICogKDIqMyk=");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(responseBody).isEqualTo("{ \n" +
                "  \"error\": \"false\",\n" +
                "  \"result\": \"-132.8889\"\n" +
                "}");
    }

    @Test
    public void shouldRejectAMalformedExpression() throws IOException {
        // 'bm90IGEgdmFsaWQgZXhwcmVzc2lvbg==' decodes to 'not a valid expression'
        whenWeMakeAGetRequestTo("/calculus?query=bm90IGEgdmFsaWQgZXhwcmVzc2lvbg==");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(responseBody).isEqualTo("{ \n" +
                "  \"error\": \"true\",\n" +
                "  \"message\": \"Invalid input 'not a valid expression'. Expressions must be composed exclusively of numbers, whitespaces, round brackets and the operators: *, /, +, -\"\n" +
                "}");
    }

    @Test
    public void shouldRejectQueriesWithInvalidEnconding() throws IOException {
        whenWeMakeAGetRequestTo("/calculus?query=this_is_not_a_valid_64_enconded_string");
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        assertThat(responseBody).isEqualTo("{ \n" +
                "  \"error\": \"true\",\n" +
                "  \"message\": \"Invalid parameter 'this_is_not_a_valid_64_enconded_string'. " +
                "The parameter must be enconded in base 64\"\n}");
    }
}
