package com.example.university.service.impl;

import com.example.university.model.Group;
import com.example.university.model.Student;
import com.example.university.repository.GroupRepository;
import com.example.university.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {
    GroupRepository groupRepository;


    @Override
    public void createGroup(String name) {
        Group group = new Group(name);
        groupRepository.save(group);
    }

    @Override
    public Optional<Group> getGroupById(long groupId) {
        return groupRepository.findById(groupId);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group updateGroupName(long groupId, String newName) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
            Group group = optionalGroup.get();
            group.setName(newName);
            return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(long groupId) {
        groupRepository.deleteById(groupId);
    }

    @Override
    public List<Student> getStudentsInGroup(long groupId)  {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
            Group group = optionalGroup.get();
            return group.getStudents();
    }
}
