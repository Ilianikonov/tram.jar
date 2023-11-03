package com.metro.tram.controller.converter;

import com.metro.tram.controller.request.TramRouteRequest;
import com.metro.tram.controller.response.TramRouteResponse;
import com.metro.tram.dto.TramRouteDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TramRouteConverter {

    public List<TramRouteResponse> balanceToGetBalanceResponse(List<TramRouteDto> tramRoutesDto){
        List<TramRouteResponse> tramRouteResponses = new ArrayList<>();
        for (TramRouteDto i:tramRoutesDto) {
            tramRouteResponses.add(convertToTramRoute(i));
        }
        return tramRouteResponses;
    }

    public TramRouteDto tramRouteRequestConvertToTramRoute(TramRouteRequest tramRouteRequest){
        TramRouteDto tramRouteDto = new TramRouteDto();
        tramRouteDto.setName(tramRouteRequest.getName());
        tramRouteDto.setCode(tramRouteRequest.getCode());
        return tramRouteDto;
    }
    public TramRouteResponse convertToTramRoute ( TramRouteDto tramRouteDto){
        TramRouteResponse tramRouteResponse = new TramRouteResponse();
        tramRouteResponse.setId(tramRouteDto.getId());
        tramRouteResponse.setName(tramRouteDto.getName());
        tramRouteResponse.setCode(tramRouteDto.getCode());
        return tramRouteResponse;
    }
}
