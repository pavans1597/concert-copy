public class ServletItem{
	private InitParam initParam;
	private String servletName;
	private String servletClass;

	public void setInitParam(InitParam initParam){
		this.initParam = initParam;
	}

	public InitParam getInitParam(){
		return initParam;
	}

	public void setServletName(String servletName){
		this.servletName = servletName;
	}

	public String getServletName(){
		return servletName;
	}

	public void setServletClass(String servletClass){
		this.servletClass = servletClass;
	}

	public String getServletClass(){
		return servletClass;
	}

	@Override
 	public String toString(){
		return 
			"ServletItem{" + 
			"init-param = '" + initParam + '\'' + 
			",servlet-name = '" + servletName + '\'' + 
			",servlet-class = '" + servletClass + '\'' + 
			"}";
		}
}
