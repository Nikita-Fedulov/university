package com.example.university.service;

import com.example.university.model.Group;
import com.example.university.model.Student;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    void createGroup(String name);

    Optional<Group> getGroupById(long groupId);

    List<Group> getAllGroups();

    Group updateGroupName(long groupId, String newName);

    void deleteGroup(long groupId);

    List<Student> getStudentsInGroup(long groupId);

}
