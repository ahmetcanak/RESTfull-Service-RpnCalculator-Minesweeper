package com.example.demo.controller;


import com.example.demo.model.MinesweeperRequest;
import com.example.demo.model.MinesweeperResponse;
import com.example.demo.model.RpnExpressionRequest;
import com.example.demo.model.RpnExpressionResponse;
import com.example.demo.service.CaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping()
public class CaseController {

    private final CaseService caseService;

    @GetMapping("/calculate")
    public ResponseEntity<RpnExpressionResponse> getExpression(@RequestBody RpnExpressionRequest expression) {
        return new ResponseEntity<>(caseService.calculatorResult(expression), OK);
    }

    @GetMapping("/minesweeper")
    public ResponseEntity<MinesweeperResponse> getHints(@RequestBody MinesweeperRequest square) {
        return new ResponseEntity<>(caseService.minesweeperResult(square), OK);
    }
}
