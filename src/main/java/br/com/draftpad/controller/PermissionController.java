package br.com.draftpad.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Permission", description = "Endpoints for Managing Permission")
@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {
}
