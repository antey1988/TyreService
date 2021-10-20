package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.dto.requests.ClientDTO;

public interface ClientService {
    Long createNewClient(String email, String phone, String name);
    Object readClient(Long id);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
}
