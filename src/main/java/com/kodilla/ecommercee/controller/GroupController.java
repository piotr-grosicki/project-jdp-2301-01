package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/groups")
public class GroupController {

    private final GroupDbService groupDbService;
    private final GroupMapper groupMapper;

    @GetMapping
    public List<GroupDto> getAllGroups() {
        ArrayList<GroupDto> groups = new ArrayList<>();
        groups.add(new GroupDto(1L, "AGD", "Sprzęt AGD", Collections.emptyList()));
        groups.add(new GroupDto(2L, "RTV", "Sprzęt RTV", Collections.emptyList()));
        return groups;
    }

    @GetMapping(value = "{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) {
        return new GroupDto();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto();
    }

    @DeleteMapping(value = "{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
    }
}
