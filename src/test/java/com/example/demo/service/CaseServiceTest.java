package com.example.demo.service;

import com.example.demo.model.MinesweeperRequest;
import com.example.demo.model.MinesweeperResponse;
import com.example.demo.model.RpnExpressionRequest;
import com.example.demo.model.RpnExpressionResponse;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class CaseServiceTest {

    @InjectMocks
    private CaseService caseService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculatorResult() {
        RpnExpressionRequest request = new RpnExpressionRequest();
        request.setExpression("20 5 /");

        RpnExpressionResponse response = new RpnExpressionResponse();
        response.setResult(4);

        Assert.assertEquals(response, caseService.calculatorResult(request));
    }

    @Test
    void minesweeperResult() {
        MinesweeperRequest request = new MinesweeperRequest();
        request.setSquare(new String[]{"**...", ".....", ".*..."});

        MinesweeperResponse response = new MinesweeperResponse();
        response.setHints(new String[]{"**100", "33200", "1*100"});

        Assert.assertEquals(response, caseService.minesweeperResult(request));
    }
}
