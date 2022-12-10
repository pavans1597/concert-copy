public class Taglib{
	private String taglibLocation;
	private String taglibUri;

	public void setTaglibLocation(String taglibLocation){
		this.taglibLocation = taglibLocation;
	}

	public String getTaglibLocation(){
		return taglibLocation;
	}

	public void setTaglibUri(String taglibUri){
		this.taglibUri = taglibUri;
	}

	public String getTaglibUri(){
		return taglibUri;
	}

	@Override
 	public String toString(){
		return 
			"Taglib{" + 
			"taglib-location = '" + taglibLocation + '\'' + 
			",taglib-uri = '" + taglibUri + '\'' + 
			"}";
		}
}
