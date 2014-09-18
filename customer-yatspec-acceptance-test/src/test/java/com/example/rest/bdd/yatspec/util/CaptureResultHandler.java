package com.example.rest.bdd.yatspec.util;

import com.googlecode.yatspec.state.givenwhenthen.CapturedInputAndOutputs;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import java.io.UnsupportedEncodingException;

import static java.util.Collections.list;

public class CaptureResultHandler implements ResultHandler {

    private final CapturedInputAndOutputs capturedInputAndOutputs;

    public CaptureResultHandler(CapturedInputAndOutputs capturedInputAndOutputs) {
        this.capturedInputAndOutputs = capturedInputAndOutputs;
    }

    @Override
    public void handle(MvcResult result) throws Exception {
        capturedInputAndOutputs.add("Request from Unknown to API", formatRequest(result.getRequest()));
        capturedInputAndOutputs.add("Response from API to Unknown", formatResponse(result.getResponse()));
    }

    private String formatRequest(MockHttpServletRequest request) {
        return new StringBuffer()
                .append("URL: ").append(request.getRequestURL()).append('\n')
                .append("Headers: ").append('\n')
                .append(formatHeaders(request))
                .toString();
    }

    private StringBuffer formatHeaders(MockHttpServletRequest request) {
        StringBuffer content = new StringBuffer();
        for(String headerName : list(request.getHeaderNames())){
            content.append('\t').append(headerName).append(" : ").append(request.getHeader(headerName)).append('\n');
        }
        return content;
    }

    private String formatResponse(MockHttpServletResponse response) throws UnsupportedEncodingException {
        return new StringBuffer()
                .append("Status: ").append(response.getStatus()).append('\n')
                .append("Body: ").append('\n')
                .append(response.getContentAsString())
                .toString();
    }
}
