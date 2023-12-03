package com.chefme.chefme.service.pointrecord;


import com.chefme.chefme.dto.pointrecord.PointRecordDTO;
import com.chefme.chefme.dto.pointrecord.PointRecordDTOMapper;
import com.chefme.chefme.exceptions.ResourceNotFoundException;
import com.chefme.chefme.model.business.Business;
import com.chefme.chefme.model.client.Client;
import com.chefme.chefme.model.pointrecord.PointRecord;
import com.chefme.chefme.repository.PointRecordRepository;
import com.chefme.chefme.responses.ResponseHandler;
import com.chefme.chefme.service.user.UserEntityService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class PointRecordServiceImpl implements  PointRecordService{

    private  final PointRecordRepository pointRecordRepository;
    private final PointRecordDTOMapper pointRecordDTOMapper;
    private  final UserEntityService userEntityService;

    public PointRecordServiceImpl(PointRecordRepository pointRecordRepository, PointRecordDTOMapper pointRecordDTOMapper, UserEntityService userEntityService) {
        this.pointRecordRepository = pointRecordRepository;
        this.pointRecordDTOMapper = pointRecordDTOMapper;
        this.userEntityService = userEntityService;
    }


    @Override
    public PointRecord save(PointRecord pointRecord) {
        return pointRecordRepository.save(pointRecord);
    }

    @Override
    public ResponseEntity<Object> addPointRecord(@NotNull UserDetails userDetails,@NotNull final String clientMail, @NotNull final long  pointsToTransmit) {
        Client exisitingClient = (Client) userEntityService.getUserEntityByEmail(clientMail);
        Business exisitingBusiness = (Business)  userEntityService.getUserEntityByEmail(userDetails.getUsername());
        PointRecord pointRecordDetails = new PointRecord();
        pointRecordDetails.setBusiness(exisitingBusiness);
        pointRecordDetails.setClient(exisitingClient);
        pointRecordDetails.setCreatedAt(LocalDate.now());
        pointRecordDetails.setCollected(false);
        pointRecordDetails.setPointsToTransmit(pointsToTransmit);
        pointRecordRepository.save(pointRecordDetails);

        PointRecordDTO pointRecord = pointRecordDTOMapper.apply(pointRecordDetails);

        return ResponseHandler.generateResponse(pointRecord , HttpStatus.OK);
    }

    @Override
    public PointRecord getPointRecordByIdAndClientId(UUID pointRecordId, UUID clientId) {
        return pointRecordRepository.fetchPointRecordByIdAndClientId(Optional.ofNullable(pointRecordId), clientId).orElseThrow(
                () -> new ResourceNotFoundException("This Point is either already collected or does not exist.")
        );
    }



}
