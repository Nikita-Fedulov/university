package com.example.university.controller;

import com.example.university.model.Group;
import com.example.university.model.Student;
import com.example.university.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/groups")
public class GroupController {

    GroupService groupService;

    @GetMapping
    public String listGroups(Model model) {
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        Map<Long, Integer> studentCountsMap = new HashMap<>();
        for (Group group : groups) {
            List<Student> students = groupService.getStudentsInGroup(group.getId());
            studentCountsMap.put(group.getId(), students.size());
        }
        model.addAttribute("studentCountsMap", studentCountsMap);
        return "group-list";
    }


    @GetMapping("/create")

    public String showGroupForm() {
        log.info("Showing group creation form");
        return "group-create";
    }


    @PostMapping("/create")
    public String createGroup(@RequestParam String name) {
        log.info("Creating group with name: {}", name);
        groupService.createGroup(name);
        return "redirect:/groups";
    }


    @GetMapping("/{groupId}")
    public String viewGroup(@PathVariable long groupId, Model model) {
        Optional<Group> group = groupService.getGroupById(groupId);
        if (group.isPresent()) {
            model.addAttribute("group", group.get());
            return "group-view";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
        }
    }


    @GetMapping("/{groupId}/edit")
    public String editGroupForm(@PathVariable long groupId, Model model) {
        log.info("Viewing group with ID: {}", groupId);
        Optional<Group> group = groupService.getGroupById(groupId);
        if (group.isPresent()) {
            model.addAttribute("group", group);
            return "group-edit";
        }
        return "redirect:/groups";
    }


    @PostMapping("/{groupId}/edit")
    public String updateGroup(@PathVariable long groupId, @RequestParam String name) {
        Group group = groupService.updateGroupName(groupId, name);
        if (group != null) {
            return "redirect:/groups/" + groupId;
        }
        return "redirect:/groups";
    }


    @GetMapping("/{groupId}/delete")
    public String deleteGroup(@PathVariable long groupId) {
        groupService.deleteGroup(groupId);
        return "redirect:/groups";
    }
}
