package com.example.loadgenerator.controller;

import com.example.loadgenerator.dto.RequestDto;
import com.example.loadgenerator.service.DataGeneratorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class LoadGenerator implements CommandLineRunner {
    private final WebClient.Builder webClientBuilder;
    private final DataGeneratorService dataGeneratorService;

    @Value("${generator.speed}")
    private int tts;
    @Value("${statistic.service.url}")
    private String url;

    @Override
    public void run(String... args) throws Exception {
        dataGeneratorService.loadData();

        Flux.interval(Duration.ofMillis(tts))
                .onBackpressureDrop()
                .flatMap(tick -> Flux.defer(() -> {
                    var request = dataGeneratorService.generate();
                    return webClientBuilder.build()
                            .post()
                            .uri(url)
                            .bodyValue(request)
                            .retrieve()
                            .bodyToMono(Void.class)
                            .doOnError(error -> {

                            })
                            .onErrorResume(error -> Mono.empty())
                            .thenReturn(request);
                }))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }


}
