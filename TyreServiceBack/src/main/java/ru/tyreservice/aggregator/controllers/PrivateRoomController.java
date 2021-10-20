package ru.tyreservice.aggregator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tyreservice.aggregator.dto.requests.ClientDTO;
import ru.tyreservice.aggregator.dto.requests.CostWorkRequestDTO;
import ru.tyreservice.aggregator.dto.requests.OrderRequestDTO;
import ru.tyreservice.aggregator.dto.requests.PartnerRequestDTO;
import ru.tyreservice.aggregator.dto.responses.CostWorkResponseDTO;
import ru.tyreservice.aggregator.dto.responses.OrderResponseDTO;
import ru.tyreservice.aggregator.enums.Role;
import ru.tyreservice.aggregator.enums.StateStatus;
import ru.tyreservice.aggregator.security.UserAccount;
import ru.tyreservice.aggregator.services.ClientService;
import ru.tyreservice.aggregator.services.CostWorkService;
import ru.tyreservice.aggregator.services.OrderService;
import ru.tyreservice.aggregator.services.PartnerService;

import java.util.List;

import static ru.tyreservice.aggregator.security.SecurityUtil.getAccount;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api")
@Tag(name = "Запросы, требующие авторизацию")
@SecurityScheme(type = SecuritySchemeType.HTTP)
public class PrivateRoomController {
    private PartnerService partnerService;
    private ClientService clientService;
    private CostWorkService costWorkService;
    private OrderService orderService;
    private ObjectMapper mapper;



    @Operation(summary = "Главная страница личного кабинета",
            description = "Основная информация в личном кабинете",
            security = @SecurityRequirement(name = "basic"))
    @GetMapping(value = "/lk")
    public Object login() {
        UserAccount account = getAccount();
        if (account.getRole() == Role.PARTNER) {
            return partnerService.readPartnerWithWorks(account.getAccountId());
        }
        return clientService.readClient(account.getAccountId());
    }

    @Operation(summary = "Обновление информации в личном кабинет",
            security = @SecurityRequirement(name = "basic"))
    @PostMapping(value = "/lk")
    public Object updateAccount(@RequestBody Object accountData) {
        UserAccount account = getAccount();
        if (account.getRole() == Role.PARTNER) {
            PartnerRequestDTO requestDTO = mapper.convertValue(accountData, PartnerRequestDTO.class);
            return partnerService.updatePartner(account.getAccountId(), requestDTO);
        }
        ClientDTO requestDTO = mapper.convertValue(accountData, ClientDTO.class);
        return clientService.updateClient(account.getAccountId(), requestDTO);
    }

    @Operation(summary = "Создание и обновление списка услуг с их стоимостью",
            security = @SecurityRequirement(name = "basic"))
    @PostMapping(value = "/lk/works")
    public List<CostWorkResponseDTO> createAndUpdateCostWork(@RequestBody List<CostWorkRequestDTO> costWorks) {
        return costWorkService.createAndUpdateCostsWorks(getAccount().getAccountId(), costWorks);
    }

    @Operation(summary = "Список заказов",
            description = "Просмотр списка заказов, исполнителем который является данный партнер" +
                    "или создателем которых является данный клиент (будет сделано в дальнейшем)",
            security = @SecurityRequirement(name = "basic"))
    @GetMapping(value = "/lk/orders")
    public List<OrderResponseDTO> readOrders() {
        UserAccount account = getAccount();
        return orderService.readListOrders(account.getAccountId(), (Role)account.getRole());
    }

    @Operation(summary = "Изменение статуса заказа",
            description = "Изменение статуса заказа партнером из личного кабинета",
            security = @SecurityRequirement(name = "basic"))
    @PostMapping(value = "/lk/orders/{id}")
    public void changeStatus(@PathVariable Long id, @RequestBody StateStatus status) {
        orderService.changeStatus(getAccount().getAccountId(), id, status);
    }

    @Operation(summary = "Создание заказа",
            description = "Создание нового заказа из кабинета клиента (будет сделано в дальнейшем)",
            security = @SecurityRequirement(name = "basic"),
            hidden = true)
    @PostMapping(value = "/lk/orders")
    public void createOrder(@RequestBody OrderRequestDTO orderRequest) {
        orderService.createOrder(orderRequest);
    }
}
