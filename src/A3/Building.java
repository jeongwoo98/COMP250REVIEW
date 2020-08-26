package A3;

import java.util.Map;

public class Building {

	OneBuilding data;
	Building older;
	Building same;
	Building younger;
	
	public Building(OneBuilding data){
		this.data = data;
		this.older = null;
		this.same = null;
		this.younger = null;
	}
	
	public String toString(){
		String result = this.data.toString() + "\n";
		if (this.older != null){
			result += "older than " + this.data.toString() + " :\n";
			result += this.older.toString();
		}
		if (this.same != null){
			result += "same age as " + this.data.toString() + " :\n";
			result += this.same.toString();
		}
		if (this.younger != null){
			result += "younger than " + this.data.toString() + " :\n";
			result += this.younger.toString();
		}
		return result;
	}

	//Notice all the recursions.
	public Building addBuilding (OneBuilding b){
		// ADD YOUR CODE HERE
		if(b.yearOfConstruction<data.yearOfConstruction){
			if(this.older==null){
				this.older = new Building(b);
			}else{
				this.older = this.older.addBuilding(b);
			}
		}

		else if(b.yearOfConstruction>data.yearOfConstruction){
			if(this.younger==null){
				this.younger = new Building(b);
			}else{
				this.younger = this.younger.addBuilding(b);
			}
		}

		else if(b.yearOfConstruction == data.yearOfConstruction){
			if(this.same == null){
				this.same = new Building(b);
			}else{
				this.same  = this.same.addBuilding(b);
			}
			//SWAPPING UP !
			if(this.same.data.height > this.data.height){
				Building temp = new Building(b);

				temp.younger = this.younger;
				temp.same = this;
				temp.older = this.older;

				this.younger = same.younger;
				this.older = same.older;
				this.same = same.same;

				return temp;
			}
		}
		return this;
	}
	
	public Building addBuildings (Building b) {
		// ADD YOUR CODE HERE
		if (b != null) {
			this.addBuilding(b.data);
			if (b.older != null) this.addBuildings(b.older);
			if (b.same != null) this.addBuildings(b.same);
			if (b.younger != null) this.addBuildings(b.younger);
		}
		return this; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public Building removeBuilding (OneBuilding b){
		// ADD YOUR CODE HERE
		if(this.data.equals(b)){
			//The new root becomes this.same :
			if(this.same!=null){
				this.data = this.same.data;
				this.same = this.same.same;
			}
			//The new root becomes b.older and you need to add the OneBuildings in b.younger to this new root:
			else if(this.older!=null){
				this.data = this.older.data;
				this.same = this.older.same;
				Building temp = this.younger;
				this.younger = this.older.younger;
				if(this.older.older!=null){
					this.older = this.older.older;
				}else{
					this.older = null;
				}
				this.addBuildings(temp);
			}
			//TODO: TEST THIS PART
			else if(this.same==null && this.older==null){
				if(this.younger!=null){
					this.data = this.younger.data;
					if(this.younger.older!=null) this.older = this.younger.older;
					else this.older = null;
					if(this.younger.same!=null)	this.same = this.younger.same;
					else this.same = null;
					if(this.younger.younger!=null)	this.younger = this.younger.younger;
					else this.younger = null;
				}else{
					return null;
				}
			}
		}else{
			if(b.yearOfConstruction == this.data.yearOfConstruction){
				if(this.same!=null) this.same = this.same.removeBuilding(b);
			}else  if(b.yearOfConstruction > this.data.yearOfConstruction){
				if(this.younger!=null) this.younger = this.younger.removeBuilding(b);
			}else if(b.yearOfConstruction < this.data.yearOfConstruction){
				if(this.older!=null) this.older = this.older.removeBuilding(b);
			}
		}

		return this; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}

	//Helper method: find building with data OneBuilding b
	public Building findBuilding(Building root, OneBuilding b){
		Building present = null;
		if(root!=null){
			if(root.older!=null){
				present = findBuilding(root.older,b);
			}
			if(root.data.equals(b)){
				return root;
			}
			if(present == null && root.same!=null){
				present = findBuilding(root.same, b);
			}
			if(root.data.equals(b)){
				return root;
			}
			if(present==null && root.younger!=null){
				present = findBuilding(root.younger,b);
			}
			if(root.data.equals(b)){
				return root;
			}
		}
		return present;
	}


	
	public int oldest(){
		// ADD YOUR CODE HERE
		if(this!=null && this.older==null){
			return this.data.yearOfConstruction;
		}else{
			return this.older.oldest();
		}
	}
	
	public int highest(){
		//Base case:
		int maxHeight =0;

		if(this!=null) {
			if (older == null && younger == null) {
				maxHeight = data.height;
			}
			if (older != null) {
				maxHeight = Math.max(data.height, older.highest());
			}
			if (younger != null) {
				maxHeight = Math.max(data.height, younger.highest());
			}
		}
		return maxHeight; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public OneBuilding highestFromYear (int year){
		// ADD YOUR CODE HERE
		OneBuilding current = null;
		if(data!=null){
			if(data.yearOfConstruction==year){
				current = data;
			}
			else if(data.yearOfConstruction<year){
				if(younger!=null){
					current = younger.highestFromYear(year);
				}else{
					current = null;
				}
			}
			else if(data.yearOfConstruction>year){
				if(older!=null){
					current = older.highestFromYear(year);
				}else{
					current = null;
				}
			}
		}
		return current;
	}
	
	public int numberFromYears (int yearMin, int yearMax){
		// ADD YOUR CODE HERE
		int nbBuildings = 0;
		if(yearMin>yearMax){
			return 0;
		}
		if(data.yearOfConstruction>=yearMin && data.yearOfConstruction<=yearMax){
			nbBuildings++;
			if(same!=null){
				nbBuildings += same.numberFromYears(yearMin,yearMax);
			}
			if(younger!=null){
				nbBuildings += younger.numberFromYears(yearMin,yearMax);
			}
			if(older!=null){
				nbBuildings+= older.numberFromYears(yearMin,yearMax);
			}
		}else{
			if(data.yearOfConstruction<yearMin){
				if(younger!=null){
					nbBuildings += younger.numberFromYears(yearMin,yearMax);
				}
			}
			if(data.yearOfConstruction>yearMax){
				if(older!=null){
					nbBuildings += older.numberFromYears(yearMin,yearMax);
				}
			}
		}
		return nbBuildings; // DON'T FORGET TO MODIFY THE RETURN IF NEEDS BE
	}
	
	public int[] costPlanning (int nbYears){
		int[] costPlan = new int[nbYears];
		if(data.yearForRepair>=2018 && data.yearForRepair<2018+nbYears){
			int index = data.yearForRepair%2018;
			costPlan[index] += data.costForRepair;
		}
		if(older!=null){
			int[] olderPlan = older.costPlanning(nbYears);
			for(int i=0; i<nbYears; i++){
				costPlan[i] += olderPlan[i];
			}
		}
		if(same!=null){
			int[] presentPlan = same.costPlanning(nbYears);
			for(int i=0; i<nbYears; i++){
				costPlan[i] += presentPlan[i];
			}
		}
		if(younger!=null){
			int[] newerPlan = younger.costPlanning(nbYears);
			for(int i=0; i<nbYears; i++){
				costPlan[i] += newerPlan[i];
			}
		}

		return costPlan;
	}
	
}
