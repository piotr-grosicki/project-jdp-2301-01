package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupDbService {

    private GroupRepository groupRepository;
}
