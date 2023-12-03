package com.chefme.chefme.dto.client;

import com.chefme.chefme.dto.point.PointDTOMapper;
import com.chefme.chefme.model.client.Client;

import java.util.function.Function;

public class ClientDTOMapper  implements Function<Client , ClientDTO> {
    @Override
    public ClientDTO apply(Client client) {
        return  new ClientDTO(
                client.getId(),
                client.getUsername(),
                client.getEmail(),
                client.getAddress(),
                client.getCreatingDate(),
                client.isEnabled(),
                client.getRole(),
                client.getPoints().stream().map(point -> new PointDTOMapper().apply(point)).toList()
        );
    }
}
