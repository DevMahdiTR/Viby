package com.chefme.chefme.service.point;

import com.chefme.chefme.dto.pointrecord.PointRecordDTOMapper;
import com.chefme.chefme.exceptions.ResourceNotFoundException;
import com.chefme.chefme.model.client.Client;
import com.chefme.chefme.model.point.Point;
import com.chefme.chefme.model.pointrecord.PointRecord;
import com.chefme.chefme.model.user.PointRepository;
import com.chefme.chefme.responses.ResponseHandler;
import com.chefme.chefme.service.pointrecord.PointRecordService;
import com.chefme.chefme.service.user.UserEntityService;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PointServiceImpl  implements  PointService{


    private final PointRepository pointRepository;
    private final PointRecordDTOMapper pointRecordDTOMapper;
    private final PointRecordService pointRecordService;

    private final UserEntityService userEntityService;
    public PointServiceImpl(PointRepository pointRepository, PointRecordDTOMapper pointRecordDTOMapper, PointRecordService pointRecordService, UserEntityService userEntityService) {
        this.pointRepository = pointRepository;
        this.pointRecordDTOMapper = pointRecordDTOMapper;
        this.pointRecordService = pointRecordService;
        this.userEntityService = userEntityService;
    }


    @Override
    public ResponseEntity<Object> collectPoints(@NotNull UserDetails userDetails, @NotNull UUID pointRecordId) {
        Client exisitingClient = (Client) userEntityService.getUserEntityByEmail(userDetails.getUsername());
        PointRecord pointRecord =  pointRecordService.getPointRecordByIdAndClientId(pointRecordId , exisitingClient.getId());

        if(isBusinessPointExistInUser(pointRecord.getBusiness().getId() , exisitingClient.getId()))
        {
            Point existingPoint =  getPointByBusinessAndClientId(pointRecord.getBusiness().getId() , exisitingClient.getId());
            existingPoint.setTotalPoints(existingPoint.getTotalPoints() + pointRecord.getPointsToTransmit());
            pointRepository.save(existingPoint);
        }else
        {
            Point newPoint = new Point();
            newPoint.setTotalPoints(pointRecord.getPointsToTransmit());
            newPoint.setBusiness(pointRecord.getBusiness());
            newPoint.setClient(exisitingClient);
            pointRepository.save(newPoint);

        }
        pointRecord.setCollected(true);
        pointRecordService.save(pointRecord);


        final String successString = "The point successfully transmitted to The User.";
        return ResponseHandler.generateResponse(successString , HttpStatus.OK);
    }

    @Override
    public boolean isBusinessPointExistInUser(@NotNull UUID businessId, @NotNull UUID clientId) {
        return pointRepository.isPointExist(businessId , clientId);
    }

    @Override
    public Point getPointByBusinessAndClientId(@NotNull final UUID businessId , @NotNull final UUID clientId)
    {
        return pointRepository.fetchPointByBusinessAndClient(businessId ,clientId).orElseThrow(
                () -> new ResourceNotFoundException("This Point could not be found is our system.")
        );
    }
}
