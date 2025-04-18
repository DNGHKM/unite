package com.hta2405.unite.service;

import com.hta2405.unite.domain.Emp;
import com.hta2405.unite.domain.Project;
import com.hta2405.unite.dto.ProjectDetailDTO;
import com.hta2405.unite.dto.ProjectRoleDTO;
import com.hta2405.unite.dto.ProjectTaskDTO;
import com.hta2405.unite.dto.ProjectTodoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public interface ProjectService {
    public List<Emp> getHiredEmpByDeptId(long deptId);

    List<Map<Long, String>> getIdToJobNameMap();

    void createProject(Project projectDTO);

    List<Project> getmainList(String userid);

    List<Project> getDoneList(String userid, int dowhat);

    void projectFavorite(int projectId, String userid);

    void projectColor(String userid, int projectId, String bgColor, String textColor);

    boolean updateProjectStatus(int projectId, String status, MultipartFile file);

    String getProjectName(int projectId);

    String getProjectContent(int projectId);

    List<ProjectDetailDTO> getProjectDetail1(int projectId, String userid);

    boolean updateTaskContent(int projectId, String memberId, String taskContent);

    boolean updateProgressRate(int projectId, String memberId, int memberProgressRate);

    List<ProjectTaskDTO> getProjectDetail2(int projectId);

    List<ProjectRoleDTO> getRole(int projectId, String userid);

    void insertToDo(String task, String userid, int projectId);

    List<ProjectTodoDTO> getTodoList(int projectId, String userid);

    boolean todoProgressRate(int projectId, String userid, int todoId, int memberProgressRate);

    boolean todoUpdate(int projectId, String userid, int todoId, String newSubject);

    boolean deleteTodo(int todoId);

    Map<String, String> getColor(int projectId, String userid);

    List<Long> updateTodoOrder(List<Long> orderedIds, int projectId, String userid);
}