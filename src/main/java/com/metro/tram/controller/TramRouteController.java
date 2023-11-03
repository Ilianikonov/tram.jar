package com.metro.tram.controller;

import com.metro.tram.controller.request.TramRouteRequest;
import com.metro.tram.controller.response.TramRouteResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TramRouteController {

    @GetMapping("/trams")
    List<TramRouteResponse> getTrams();
    @GetMapping("/trams/{id}")
    TramRouteResponse getTramsById(@PathVariable long id);
    @PostMapping("/trams")
    long createTrams(@RequestBody TramRouteRequest tramRouteRequest);
    @DeleteMapping("/trams/{id}")
    void deleteTramById(@PathVariable long id);
    @PatchMapping("/trams/{id}")
    TramRouteResponse updateTramRoute(@PathVariable Long id, @RequestBody TramRouteRequest tramRouteRequest);
}
