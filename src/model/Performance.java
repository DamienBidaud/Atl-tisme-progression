package model;

public class Performance {

	private String parcours;
	private String temps;
	private String date;
	private String[] parc = {"bagatelle", "pont de neuilly", "bagatelle + t1", "ile de la jatte"};
	
	public Performance(int parcours, String temps, String date){
		this.parcours = matchParcours(parcours);
		this.temps = temps;
		this.date = date;
	}

	public String getParcours() {
		return parcours;
	}

	public void setParcours(String parcours) {
		this.parcours = parcours;
	}
	
	public void setParcours(int parcours){
		this.parcours = matchParcours(parcours);
	}

	public String getTemps() {
		return temps;
	}

	public void setTemps(String temps) {
		this.temps = temps;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString(){
		return "Temps:"+this.temps+"; Date:"+this.date+"; parcours:"+this.parcours;
	}
	
	public String matchParcours(int parcour){
		return parc[parcour-1];
	}
}
