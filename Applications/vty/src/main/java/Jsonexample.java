public class Jsonexample{
	private WebApp webApp;

	public void setWebApp(WebApp webApp){
		this.webApp = webApp;
	}

	public WebApp getWebApp(){
		return webApp;
	}

	@Override
 	public String toString(){
		return 
			"Jsonexample{" + 
			"web-app = '" + webApp + '\'' + 
			"}";
		}
}
