package zzleep.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zzleep.core.models.*;
import zzleep.core.repositories.SleepRepository;
import zzleep.core.repositories.TestRepository;

@RestController
@RequestMapping("/api/sleeps")
@Api(value = "User sleep api")
public class SleepController {

    private final SleepRepository sleepRepository;

    public SleepController(SleepRepository sleepRepository) {
        this.sleepRepository = sleepRepository;
    }

    @ApiOperation(value = "Start sleep tracking", response = Sleep.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully started tracking the room conditions"),
    })
    @PostMapping("/startTracking/{deviceId}")
    public ResponseEntity<Sleep> startTracking(@PathVariable(value="deviceId") String deviceId)
    {
        Sleep sleep;
        try {
            sleep = sleepRepository.startTracking(deviceId);
            return ResponseEntity
                    .status(200)
                    .body(sleep);
        }catch(SleepRepository.SleepNotStoppedException ex)
        {
            return ResponseEntity
                    .status(409)
                    .body(null);
        }
    }

    @ApiOperation(value = "Stop sleep tracking", response = Sleep.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully started tracking the room conditions"),
    })
    @PutMapping("/stopTracking/{deviceId}")
    public ResponseEntity<Sleep> stopTracking(@PathVariable(value="deviceId") String deviceId)
    {
        Sleep sleep;
        try {
            sleep = sleepRepository.stopTracking(deviceId);
            return ResponseEntity
                    .status(200)
                    .body(sleep);
        }catch(SleepRepository.SleepNotStartedException ex)
        {
            return ResponseEntity
                    .status(404)
                    .body(null);
        }
    }

    @ApiOperation(value = "Stop sleep tracking", response = Sleep.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully started tracking the room conditions"),
    })
    @PutMapping("/{sleepId}/{rating}")
    public ResponseEntity<Sleep> setRating(@PathVariable(value="sleepId") String sleepId, @PathVariable(value="rating") int rating)
    {
        Sleep sleep;
        try {
            sleep = sleepRepository.rateSleep(sleepId, rating);
            return ResponseEntity
                    .status(200)
                    .body(sleep);
        }catch(SleepRepository.SleepNotFoundException ex)
        {
            return ResponseEntity
                    .status(404)
                    .body(null);
        }
    }
}
