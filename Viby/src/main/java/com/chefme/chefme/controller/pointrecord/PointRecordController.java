package com.chefme.chefme.controller.pointrecord;

import com.chefme.chefme.service.pointrecord.PointRecordService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/point_record")
@RestController
public class PointRecordController {

    private final PointRecordService pointRecordService;

    public PointRecordController(PointRecordService pointRecordService) {
        this.pointRecordService = pointRecordService;
    }


    @PostMapping("/{clientMail}")
    public ResponseEntity<Object> addPointRecord(
            @AuthenticationPrincipal @NotNull UserDetails userDetails,
            @PathVariable("clientMail") @NotNull final String clientMail,
            @NotNull @RequestParam(name = "pointsToTransmit" ,required = true) final long pointsToTransmit
            )
    {
        return pointRecordService.addPointRecord(userDetails , clientMail, pointsToTransmit );
    }
}
