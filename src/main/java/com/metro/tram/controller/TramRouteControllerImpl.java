package com.metro.tram.controller;

import com.metro.tram.controller.converter.TramRouteConverter;
import com.metro.tram.controller.request.TramRouteRequest;
import com.metro.tram.controller.response.TramRouteResponse;
import com.metro.tram.dto.TramRouteDto;
import com.metro.tram.service.TramRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TramRouteControllerImpl implements TramRouteController {
    private final TramRouteService tramRouteService;
    private final TramRouteConverter tramRouteConverter;

    public List<TramRouteResponse> getTrams() {
        return tramRouteConverter.balanceToGetBalanceResponse(tramRouteService.getTrams());
    }

    public TramRouteResponse getTramsById(long id) {
        return tramRouteConverter.convertToTramRoute(tramRouteService.getTramsById(id));
    }

    public long createTrams(TramRouteRequest tramRouteRequest) {
        return tramRouteService.createTram(tramRouteConverter.tramRouteRequestConvertToTramRoute(tramRouteRequest));
    }

    public void deleteTramById(long id) {
        tramRouteService.deleteTramById(id);
    }

    public TramRouteResponse updateTramRoute(Long id, TramRouteRequest tramRouteRequest) {
        TramRouteDto tramRouteDto = tramRouteConverter.tramRouteRequestConvertToTramRoute(tramRouteRequest);
        tramRouteDto.setId(id);
        return tramRouteConverter.convertToTramRoute(tramRouteService.updateTramRoute(tramRouteDto));
    }
}
