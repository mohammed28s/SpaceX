package com.spaceX.Space.X.Rocket.Service;





import com.spaceX.Space.X.Rocket.Entity.LaunchEntity;
import com.spaceX.Space.X.Rocket.Exception.SpaceXApiException;
import com.spaceX.Space.X.Rocket.Repository.LaunchRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class SpaceXLaunchService {

    private final WebClient webClient;
    private final LaunchRepository launchRepository;

    public SpaceXLaunchService(WebClient.Builder builder, LaunchRepository repo) {
        // ✅ Base URL for SpaceX API
        this.webClient = builder.baseUrl("https://api.spacexdata.com/v5").build();  // this is the main path for the spaceX API
        this.launchRepository = repo;
    }

    // Fetch all launches (raw JSON)
    public String getAllLaunches() {
        return fetch("/launches");
    }

    // Fetch latest launch
    public String getLatestLaunch() {
        return fetch("/launches/latest");
    }

    // Fetch upcoming launches
    public String getUpcomingLaunches() {
        return fetch("/launches/upcoming");
    }

    // Fetch past launches
    public String getPastLaunches() {
        return fetch("/launches/past");
    }

    // Sync launches into Neon DB
    public void syncLaunches() {
        List<LaunchEntity> launches = webClient.get()
                .uri("/launches")
                .retrieve()
                .bodyToFlux(LaunchEntity.class)
                .collectList()
                .block();

        if (launches != null) {
            launchRepository.saveAll(launches);
        }
    }

    // Generic fetch method with error handling
    private String fetch(String path) {
        try {
            return webClient.get()
                    .uri(path)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response ->
                            response.bodyToMono(String.class)
                                    .flatMap(errorBody -> Mono.error(
                                            new SpaceXApiException(errorBody, response.statusCode().value())
                                    ))
                    )
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            throw new SpaceXApiException("Unable to connect to SpaceX API", 500);
        }
    }
}
