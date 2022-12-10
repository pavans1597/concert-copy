public class WebApp{
	private ServletMapping servletMapping;
	private Taglib taglib;
	private List<ServletItem> servlet;

	public void setServletMapping(ServletMapping servletMapping){
		this.servletMapping = servletMapping;
	}

	public ServletMapping getServletMapping(){
		return servletMapping;
	}

	public void setTaglib(Taglib taglib){
		this.taglib = taglib;
	}

	public Taglib getTaglib(){
		return taglib;
	}

	public void setServlet(List<ServletItem> servlet){
		this.servlet = servlet;
	}

	public List<ServletItem> getServlet(){
		return servlet;
	}

	@Override
 	public String toString(){
		return 
			"WebApp{" + 
			"servlet-mapping = '" + servletMapping + '\'' + 
			",taglib = '" + taglib + '\'' + 
			",servlet = '" + servlet + '\'' + 
			"}";
		}
}
