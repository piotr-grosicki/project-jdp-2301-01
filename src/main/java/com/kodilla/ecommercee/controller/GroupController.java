package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/groups")
public class GroupController {

    private final GroupDbService groupDbService;
    private final GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<Group> groups = groupDbService.getAllGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupsDto(groups));
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        return ResponseEntity.ok(groupMapper.mapToGroupDto(groupDbService.getGroup(groupId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        groupDbService.saveGroup(group);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) throws GroupNotFoundException{
        Group group = groupDbService.getGroup(groupDto.getGroupId());
        group.setGroupName(groupDto.getGroupName());
        group.setGroupDescription(groupDto.getGroupDescription());
        group.setProducts(groupMapper.findAllById(groupDto.getProductsId()));
        Group updatedGroup = groupDbService.saveGroup(group);
        return ResponseEntity.ok(groupMapper.mapToGroupDto(updatedGroup));
    }

    @DeleteMapping(value = "{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        groupDbService.deleteGroup(groupId);
        return ResponseEntity.ok().build();
    }
}
