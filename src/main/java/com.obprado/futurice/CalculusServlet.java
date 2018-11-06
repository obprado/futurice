package com.obprado.futurice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;

import static java.lang.String.format;

public class CalculusServlet extends HttpServlet {

    private static final String CALCULATION_RESPONSE_TEMPLATE = "{ \n" +
            "  \"error\": \"false\",\n" +
            "  \"result\": \"%s\"\n" +
            "}";

    private static final String ERROR_TEMPLATE = "{ \n" +
            "  \"error\": \"true\",\n" +
            "  \"message\": \"%s\"\n" +
            "}";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String base64encondedExpression = request.getParameter("query");

        try {
            String query = new String(Base64.getDecoder().decode(base64encondedExpression.getBytes()));
            BigDecimal result = new Calculator().calculate(query);
            response.getWriter().write(format(CALCULATION_RESPONSE_TEMPLATE, result));
        } catch (CalculusException e) {
            response.getWriter().write(format(ERROR_TEMPLATE, e.getMessage()));
        } catch (IllegalArgumentException e) {
            response.getWriter().write(format(ERROR_TEMPLATE,
                    format("Invalid parameter '%s'. The parameter must be enconded in base 64", base64encondedExpression)
            ));
        }

    }

}
