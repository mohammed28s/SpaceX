package com.spaceX.Space.X.Rocket.Controller;



import com.spaceX.Space.X.Rocket.Service.SpaceXLaunchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/spacex")
@Tag(name = "SpaceX Launches", description = "Information about SpaceX launches")
public class SpaceXLaunchController {

    private final SpaceXLaunchService launchService;

    public SpaceXLaunchController(SpaceXLaunchService launchService) {
        this.launchService = launchService;
    }

    @Operation(summary = "Get all launches")
    @GetMapping("/launches")
    public String getAllLaunches() {
        return launchService.getAllLaunches();
    }

    @Operation(summary = "Get latest launch")
    @GetMapping("/launches/latest")
    public String getLatestLaunch() {
        return launchService.getLatestLaunch();
    }

    @Operation(summary = "Get upcoming launches")
    @GetMapping("/launches/upcoming")
    public String getUpcomingLaunches() {
        return launchService.getUpcomingLaunches();
    }

    @Operation(summary = "Get past launches")
    @GetMapping("/launches/past")
    public String getPastLaunches() {
        return launchService.getPastLaunches();
    }

    @Operation(summary = "Sync launches into Neon DB")
    @PostMapping("/launches/sync")
    public String syncLaunches() {
        launchService.syncLaunches();
        return "Launch data synced into Neon DB!";
    }
}
