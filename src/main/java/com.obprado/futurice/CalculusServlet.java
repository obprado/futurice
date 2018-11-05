package com.obprado.futurice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;

public class CalculusServlet extends HttpServlet {

    private static final String CALCULATION_RESPONSE_TEMPLATE = "{ \n" +
            "  \"error\": \"false\",\n" +
            "  \"result\": \"%s\"\n" +
            "}";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        byte[] encodedQuery = request.getParameter("query").getBytes();
        String query = new String(Base64.getDecoder().decode(encodedQuery));
        BigDecimal result = new Calculator().calculate(query);

        response.getWriter().write(String.format(CALCULATION_RESPONSE_TEMPLATE, result));
    }

}
