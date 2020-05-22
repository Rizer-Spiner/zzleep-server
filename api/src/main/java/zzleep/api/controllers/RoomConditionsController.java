package zzleep.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zzleep.core.models.RoomCondition;
import zzleep.core.repositories.RoomConditionsRepository;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("/api/room-conditions")
@Api(tags = {"Room Conditions"}, description = " ")
public class RoomConditionsController extends ControllerBase {

    private static int[] temperatures = {26, 27, 27, 26};
    private static int[] humidities = {45, 45, 46, 45};
    private static int count = 0;

    private final RoomConditionsRepository repository;

    public RoomConditionsController(RoomConditionsRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Get current room condition", response = RoomCondition.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved current room condition"),
    })
    @GetMapping("/{deviceId}")
    public ResponseEntity<RoomCondition> getReport(@PathVariable(name = "deviceId") String deviceId) {
        RoomCondition rc = new RoomCondition(
            1, LocalDateTime.now(),
            temperatures[count],
            250, 70,
            humidities[count]
        );
        count = (count + 1) % 4;
        return success(rc);
        // try{
        //     rc = repository.getCurrentData(deviceId);
        //     return ResponseEntity
        //             .status(200)
        //             .body(rc);
        // }
        // catch(RoomConditionsRepository.SleepNotFoundException ex)
        // {
        //     return ResponseEntity
        //             .status(404)
        //             .body(null);
        // }
        // catch(RoomConditionsRepository.NoDataException ex)
        // {
        //     return ResponseEntity
        //             .status(200)
        //             .body(null);
        // }
    }
}
