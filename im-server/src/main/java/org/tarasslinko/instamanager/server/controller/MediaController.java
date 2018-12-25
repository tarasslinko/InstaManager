package org.tarasslinko.instamanager.server.controller;

import org.tarasslinko.instamanager.server.service.MediaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("media")
public class MediaController {

    private MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @RequestMapping(value = "getSelf", produces = "text/plain")
    public String getSelf() {
        return mediaService.getSelf();
    }
    @RequestMapping(value = "getSelfMedia", produces = "text/plain")
    public String getSelfMedia() {
        return mediaService.getSelfMedia();
    }
}
