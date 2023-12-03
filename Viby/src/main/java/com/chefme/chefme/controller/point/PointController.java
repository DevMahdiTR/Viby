package com.chefme.chefme.controller.point;

import com.chefme.chefme.service.point.PointService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/point")
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }


    @PostMapping("/{pointRecordId}")
    public ResponseEntity<Object> collectPoints(
            @AuthenticationPrincipal @NotNull UserDetails userDetails,
            @PathVariable("pointRecordId") @NotNull UUID pointRecordId
    )
    {
        return pointService.collectPoints(userDetails,pointRecordId);
    }


}