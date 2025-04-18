package com.hta2405.unite.mybatis.mapper;

import com.hta2405.unite.domain.Emp;
import com.hta2405.unite.domain.Project;
import com.hta2405.unite.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper {
    List<Emp> getHiredEmpByDeptId(long deptId); //글의 갯수 구하기

    List<Map<Long, String>> getIdToJobNameMap();

    void createProject(Project project);

    void addProjectMember(int projectId, String memberName, String memberId, String role);

    void createTask(int projectId, String empId, String memberName);

    List<Project> getMainList(String userid);
    List<Project> getDoneList(HashMap<String, Object> map);
//    List<Project> getCompleteList(HashMap<String, Object> map);

    void projectFavorite(int projectId, String userid);

    void projectColor(String userid, int projectId, String bgColor, String textColor);

    boolean projectStatus(int projectId, FileDTO taskFile, int complete, int cancel);

    String getProjectName(int projectId);
    String getProjectContent(int projectId);

    List<ProjectDetailDTO> getProjectDetail1(int projectId, String userid);

    boolean updateTaskContent(int projectId, String memberId, String taskContent);

    boolean updateProgressRate(int projectId, String memberId, int memberProgressRate);

    List<ProjectTaskDTO> getProjectDetail2(int projectId);

    List<ProjectRoleDTO> getRole(int projectId, String userid);
    void insertToDo(String task, String userid, int projectId);
    List<ProjectTodoDTO> getTodoList(int projectId, String userid);
    boolean updateTodoProgressRate(int projectId, String userid, int todoId, int memberProgressRate);
    boolean todoUpdate(int projectId, String userid, int todoId, String newSubject);
    boolean deleteTodo(int todoId);
    Project findMemberInfoById(int projectId, String userid);

    void updateTodoOrder(ProjectTodoDTO orderedTodos);
}
