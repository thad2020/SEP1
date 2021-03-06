package Model;

import parser.XmlJsonParser;

import java.io.File;
import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class ColourIT
{
  // Bruges lige til opsætning af View Handler

  private TeamMember currentUser;
  private Project selectedProject;
  private Requirement selectedRequirement;
  private Task selectedTask;
  private TeamMember selectedTeamMember;
  private XmlJsonParser theParser;

  private ProjectList projectList;
  private CustomerList customerList;
  private EmployeeList employeeList;
  final private String[] typeList = {"Functional", "Non-functional", "Project requirement"};

  public ColourIT()
  {
    projectList = new ProjectList();
    customerList = new CustomerList();
    employeeList = new EmployeeList();

    // vi starter også lige en parser, det kan vi godt lide
    theParser = new XmlJsonParser();

    /*
     *  Når vi har lavet persist data med filer
     *  så skal data vel indlæses her ;)
     */
    settetTest(); // Vores dummy data
  }

  public TeamMember getCurrentUser()
  {
    return currentUser;
  }

  public void setCurrentUser(TeamMember currentUser)
  {
    this.currentUser = currentUser;
  }

  public void createNewProject(String title, Customer customer,
      TeamMember projectCreator)
  {
    projectList.createNewProject(title, customer, projectCreator);

    File projectlist = theParser
        .toXml(projectList.getProjects(), projectlist.xml);
  }

  public void addNewTeamMemberToProject(TeamMember teamMember, Project project)
  {
    projectList.addNewTeamMemberToProject(teamMember, project);
  }

  public void addRequirement(Project project, Requirement requirement)
  {
    projectList.addRequirement(project, requirement);
  }

  public void addTask(Project project, Requirement requirement, Task task)
  {
    projectList.addTask(project, requirement, task);
  }

  public void addTeamMemberToTask(Project project, Requirement requirement, Task task, TeamMember teamMember)
  {
    projectList.addTeamMemberToTask(project, requirement, task, teamMember);
  }

  public void prioritizeRequirement(String value)
  {
    projectList.prioritizeRequirement(value);
  }

  public void prioritizeTask(String value)
  {
    projectList.prioritizeTask(value);
  }

  public void approveRequirement(Project project, Requirement requirement, String value)
  {
    projectList.approveRequirement(project, requirement, value);
  }

  public void addRoleToProject(TeamMember teamMember, Project project)
  {
    projectList.addRoleToProject(teamMember, project);
  }

  public ArrayList<Task> getAllTasks(Project project, Requirement requirement)
  {
    return projectList.getAllTasks(project, requirement);
  }

  public ArrayList<Task> getAllTasks(Project project)
  {
    return projectList.getAllTasks(project);
  }

  // TODO getAllTasks(TeamMember teamMember)

  public void registerTime(TeamMember teamMember, Task task, int time)
  {
    // TODO projectList.registerTime(teamMember, task, time);
    teamMember.registerTime(time);
  }

  public  void updateTime(TeamMember teamMember, Task task, int time)
  {
    // TODO projectList.updateTime(teamMember, task, time)
    teamMember.updateTime(time);
  }

  public void updateStatus(Project project, Requirement requirement, Task task,
      String value)
  {
    projectList.updateStatus(project, requirement, task, value);
  }

  public Project searchProject(String title)
  {
    return projectList.searchProject(title);
  }

  public void updateRequirement(Project project, Requirement requirement,
      String title, String description, String type, int estimatedTime,
      Date deadline, TeamMember responsibleTeamMember)
  {
    projectList.updateRequirement(project, requirement, title, description, type,
        estimatedTime, deadline, responsibleTeamMember);
  }

  public ArrayList<Task> searchTask(Project project, Requirement requirement, String title)
  {
    return projectList.searchTask(project, requirement, title);
  }

  public ArrayList<TeamMember> searchEmployee(String name)
  {
    // TODO der foregår et eller andet her
    return employeeList.searchEmployee(name);
  }

  public void updateTask(Project project, Requirement requirement, Task task,
      String title, String description, int estimatedTime, Date deadline, TeamMember responsibleTeamMember)
  {
    projectList.updateTask(project, requirement, task, title, description, estimatedTime
    , deadline, responsibleTeamMember);
  }

  /*
   *  Herunder er methoder vi bruger for at få data til views!
   */
  public void setSelectedProject(Project selectedProject)
  {
    this.selectedProject = selectedProject;
  }

  public Project getSelectedProject()
  {
    return selectedProject;
  }

  public void setSelectedRequirement(Requirement requirement)
  {
    this.selectedRequirement = requirement;
  }

  public Requirement getSelectedRequirement()
  {
    return selectedRequirement;
  }

  public Task getSelectedTask()
  {
    return selectedTask;
  }

  public void setSelectedTask(Task selectedTask)
  {
    this.selectedTask = selectedTask;
  }

  public TeamMember getSelectedTeamMember()
  {
    return selectedTeamMember;
  }

  public void setSelectedTeamMember(TeamMember selectedTeamMember)
  {
    this.selectedTeamMember = selectedTeamMember;
  }

  public ArrayList<Project> getProjects()
  {
    return projectList.getProjects();
  }

  public ArrayList<Requirement> getAllRequirements()
  {
    return projectList.getRequirements();
  }

  public ArrayList<TeamMember> getEmployees()
  {
    return employeeList.getEmployees();
  }



  /*
   *  DUMMY D>TA
   */
  public void settetTest()
  {
    // Nogle kunder
    Customer customer1 = new Customer("Jimifer Jensen");
    Customer customer2 = new Customer("Jack Black");
    customerList.addCustomer(customer1);
    customerList.addCustomer(customer2);

    // Nogle medarbejdere
    TeamMember teamMember1 = new TeamMember("John Doe");
    TeamMember teamMember2 = new TeamMember("Jane Doe");
    employeeList.addEmployee(teamMember1);
    employeeList.addEmployee(teamMember2);

    // Nogle projekter
    //Project project1 = new Project("Best Project ever!", customer1, teamMember1);
    //Project project2 = new Project("Colour IT PMS", customer2, teamMember2);
    this.createNewProject("Best Project ever!", customer1, teamMember1);
    this.createNewProject("Colour IT PMS", customer2, teamMember2);
    //  Tilføj Team Members
    this.addNewTeamMemberToProject(teamMember2, projectList.searchProject("Best Project ever!"));
    this.addNewTeamMemberToProject(teamMember1, projectList.searchProject("Colour IT PMS"));

    //  Nogle requirements
    // To til projekt 1
    Requirement prj1Requirement1 = new Requirement("Find a fish", "Started", "Functional Requirement");
    Requirement prj1Requirement2 = new Requirement("Slap someone with fish", "Not Started", "Functional Requirement");
    this.addRequirement(projectList.searchProject("Best Project ever!"), prj1Requirement1);
    this.addRequirement(projectList.searchProject("Best Project ever!"), prj1Requirement2);
    // To til projekt 2
    Requirement prj2Requirement1 = new Requirement("Blow a rainbow", "Started", "Functional Requirement");
    Requirement prj2Requirement2 = new Requirement("Colour that rainbow", "Not Started", "Non Requirement");
    this.addRequirement(projectList.searchProject("Colour IT PMS"), prj2Requirement1);
    this.addRequirement(projectList.searchProject("Colour IT PMS"), prj2Requirement2);

    // Tasks til requirements
    // projekt 1 requirement 1
    Task prj1Req1Task1 = new Task("Find a fishing hole", "Hah, you wish");
    Task prj1Req1Task2 = new Task("Remember fishing pole", "Doh!");
    this.addTask(
        projectList.searchProject("Best Project ever!"),
        projectList.searchProject("Best Project ever!").getRequirementList().get(0),
        prj1Req1Task1
    );
    this.addTask(
        projectList.searchProject("Best Project ever!"),
        projectList.searchProject("Best Project ever!").getRequirementList().get(0),
        prj1Req1Task2
    );
    // projekt 1 requirement 2
    Task prj1Req2Task1 = new Task("Find someone to slap with a fish", "Hah, you wish");
    Task prj1Req2Task2 = new Task("Someone found you first!", "F*****ck");
    this.addTask(
        projectList.searchProject("Best Project ever!"),
        projectList.searchProject("Best Project ever!").getRequirementList().get(1),
        prj1Req2Task1
    );
    this.addTask(
        projectList.searchProject("Best Project ever!"),
        projectList.searchProject("Best Project ever!").getRequirementList().get(1),
        prj1Req2Task2
    );
    // projekt 2 requirement 1
    Task prj2Req1Task1 = new Task("Find someone who is abel to blow a rainbow", "Hah, you wish");
    Task prj2Req1Task2 = new Task("Bribe that person with candy", "Not Started");
    this.addTask(
        projectList.searchProject("Colour IT PMS"),
        projectList.searchProject("Colour IT PMS").getRequirementList().get(0),
        prj2Req1Task1
    );
    this.addTask(
        projectList.searchProject("Colour IT PMS"),
        projectList.searchProject("Colour IT PMS").getRequirementList().get(0),
        prj2Req1Task2
    );
    // projekt 2 requirement 1
    Task prj2Req2Task1 = new Task("Take a red crayon", "Started");
    Task prj2Req2Task2 = new Task("Find a rainbow coloured crayon!", "YAY I WIN!");
    this.addTask(
        projectList.searchProject("Colour IT PMS"),
        projectList.searchProject("Colour IT PMS").getRequirementList().get(1),
        prj2Req2Task1
    );
    this.addTask(
        projectList.getProjects().get(1),
        projectList.getProjects().get(1).getRequirementList().get(1),
        prj2Req2Task2
    );
  }

  public void addNewTeamMember(TeamMember newTeamMember)
  {
    employeeList.addEmployee(newTeamMember);
  }

  public void addNewCustomer(Customer customer)
  {
    customerList.addCustomer(customer);
  }

  public ArrayList<Customer> getCustomers()
  {
    return customerList.getCustomers();
  }

  public String[] getRequirementTypes()
  {
    return typeList;
  }
}
