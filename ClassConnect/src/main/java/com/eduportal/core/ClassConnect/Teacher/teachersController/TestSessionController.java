package com.eduportal.core.ClassConnect.Teacher.teachersController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController  // Use RestController to return JSON responses
@RequestMapping("/teachers")
public class TestSessionController {

    private static final Logger logger = LoggerFactory.getLogger(TestSessionController.class);

    @GetMapping("/session-attributes")
    public List<Map<String, Object>> printSessionAttributes(HttpSession session) {
        List<Map<String, Object>> sessionData = new ArrayList<>();

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("username", session.getAttribute("username"));
        userDetails.put("email", session.getAttribute("email"));
        userDetails.put("teacher_id", session.getAttribute("teacher_id"));
        userDetails.put("sessionId", session.getId());

        sessionData.add(userDetails);

        logger.info("Session Attributes: " + userDetails);

        return sessionData;  // Returns JSON response
    }
}
