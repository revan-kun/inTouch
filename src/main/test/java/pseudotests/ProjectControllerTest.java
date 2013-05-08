package pseudotests;

import com.epam.lab.intouch.controller.exception.DataAccessingException;
import com.epam.lab.intouch.controller.project.ProjectController;
import com.epam.lab.intouch.model.project.Project;

public class ProjectControllerTest {

	/**
	 * @param args
	 * @throws DataAccessingException
	 */
	public static void main(String[] args) throws DataAccessingException {
		Project project = new ProjectController().getProject(4L);
		System.out.println(project);
	}

}
