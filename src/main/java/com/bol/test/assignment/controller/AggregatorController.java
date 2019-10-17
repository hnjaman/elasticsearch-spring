package com.bol.test.assignment.controller;

import com.bol.test.assignment.aggregator.AggregatorService;
import com.bol.test.assignment.aggregator.EnrichedOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregatorController {
    private AggregatorService aggregatorService;

    @Autowired
    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @GetMapping("/aggregators/{sellerId}")
    public EnrichedOrder enrichedOrder(@PathVariable("sellerId") Integer sellerId){
        return aggregatorService.enrich(sellerId);
    }
}
