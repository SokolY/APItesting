import java.io.IOException;

import files.DataDriven;

public class TestExecution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DataDriven data = new DataDriven();
		System.out.println(data.getData("Login", "Profile").get(1));
		System.out.println(data.getData("Addbook", "Books").get(1));
//		System.out.println(data.getData("Delete Profile").size());

	}

}
