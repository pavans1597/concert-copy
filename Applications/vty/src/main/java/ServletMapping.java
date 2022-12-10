public class ServletMapping{
	private String cofaxAdmin;
	private String cofaxCDS;
	private String cofaxEmail;
	private String fileServlet;
	private String cofaxTools;

	public void setCofaxAdmin(String cofaxAdmin){
		this.cofaxAdmin = cofaxAdmin;
	}

	public String getCofaxAdmin(){
		return cofaxAdmin;
	}

	public void setCofaxCDS(String cofaxCDS){
		this.cofaxCDS = cofaxCDS;
	}

	public String getCofaxCDS(){
		return cofaxCDS;
	}

	public void setCofaxEmail(String cofaxEmail){
		this.cofaxEmail = cofaxEmail;
	}

	public String getCofaxEmail(){
		return cofaxEmail;
	}

	public void setFileServlet(String fileServlet){
		this.fileServlet = fileServlet;
	}

	public String getFileServlet(){
		return fileServlet;
	}

	public void setCofaxTools(String cofaxTools){
		this.cofaxTools = cofaxTools;
	}

	public String getCofaxTools(){
		return cofaxTools;
	}

	@Override
 	public String toString(){
		return 
			"ServletMapping{" + 
			"cofaxAdmin = '" + cofaxAdmin + '\'' + 
			",cofaxCDS = '" + cofaxCDS + '\'' + 
			",cofaxEmail = '" + cofaxEmail + '\'' + 
			",fileServlet = '" + fileServlet + '\'' + 
			",cofaxTools = '" + cofaxTools + '\'' + 
			"}";
		}
}
