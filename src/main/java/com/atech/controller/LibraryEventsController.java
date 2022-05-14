package com.atech.controller;

import com.atech.domain.LibraryEvent;
import com.atech.domain.LibraryEventType;
import com.atech.producer.LibraryEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestController
@AllArgsConstructor
public class LibraryEventsController {

    private final LibraryEventProducer libraryEventProducer;

    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {

        libraryEvent.setLibraryEventType(LibraryEventType.NEW);

        //invoke Kafka producer
        log.info("Before Sending Library Event");

        libraryEventProducer.sendLibraryEventsAsyncTwo(libraryEvent);
        log.info("After Sending Library Event");

        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}

//      libraryEventProducer.sendLibraryEventsAsync(libraryEvent);
//      SendResult<Integer, String> result = libraryEventProducer.sendLibraryEventSynchronous(libraryEvent);
//      log.info("Send Result : " + result.toString());
