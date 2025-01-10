package com.hta2405.unite.service;

import com.hta2405.unite.domain.ProjectTask;
import com.hta2405.unite.dto.ProjectTaskDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ProjectBoardService {
    boolean insertOrUpdate(String title, String content, String userid, int projectId, MultipartFile file, String category);
    List<ProjectTaskDTO> getRecentPosts(int projectId);
    List<ProjectTask> getTaskList(int projectId, String userid);
    List<ProjectTask> getTask(int projectId, String userid, int taskId);
}